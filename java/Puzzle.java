import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle
{
    static final int FACE_TOTAL = 555;

    private ArrayList<Cube> cubes = new ArrayList<>();

    public Puzzle(int[][] cubes)
    {
        for (int[] cubeValues : cubes) {
            this.cubes.add(new Cube(cubeValues));
        }
    }

    public Puzzle(ArrayList<Cube> cubes)
    {
        for (Cube cube : cubes) {
            this.cubes.add(new Cube(cube));
        }
    }

    public Puzzle(Puzzle p)
    {
        for (Cube cube : p.getCubes()) {
            this.cubes.add(new Cube(cube));
        }
    }

    public ArrayList<Cube> getCubes()
    {
        return cubes;
    }

    public int getNumberOfColumns()
    {
        if (this.cubes.size() == 0) {
            return 0;
        }

        return cubes.get(0).getValues().length;
    }

    @Override
    public String toString()
    {
        String output = "";

        for (Cube cube : cubes) {
            output += cube + "\n";
        }

        return output;
    }

    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }

    public static ArrayList<Puzzle> solve(Puzzle puzzle)
    {
        ArrayList<CubePermutation> cubePermutations = Puzzle.getCubePermutations(puzzle, 0);
        cubePermutations.removeIf(cubeValues -> !Puzzle.hasCorrectSum(cubeValues));

        HashMap<Integer, Puzzle> correctSolutions = new HashMap<>();

        for (CubePermutation permutation : cubePermutations) {
            ArrayList<Puzzle> possibleSolutions = Puzzle.getPossibleSolutions(puzzle, permutation);

            for (Puzzle solution : possibleSolutions) {
                if (Puzzle.checkSolution(solution)) {
                    if (correctSolutions.containsKey(solution.hashCode())) {
                        continue;
                    }

                    correctSolutions.put(solution.hashCode(), solution);
                }
            }
        }

        return new ArrayList<>(correctSolutions.values());
    }

    public static ArrayList<CubePermutation> getCubePermutations(Puzzle p, int index)
    {
        assert index >= 0;

        ArrayList<CubePermutation> permutations = new ArrayList<>();

        ArrayList<Cube> cubes = p.getCubes();

        int numCubes = cubes.size();

        for (int i = 0; i < numCubes; i++) {
            int[] values = cubes.get(i).getValues();

            if (i == 0) {
                values = new int[] { values[index] };
            }

            permutations = appendCubePermutations(permutations, values);
        }

        return permutations;
    }

    private static ArrayList<CubePermutation> appendCubePermutations(ArrayList<CubePermutation> permutations, int[] values)
    {
        if (permutations.isEmpty()) {
            for (int value : values) {
                permutations.add(new CubePermutation() {{
                    add(value);
                }});
            }

            return permutations;
        }

        ArrayList<CubePermutation> result = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            ArrayList<CubePermutation> tempPermutations = new ArrayList<>();

            // need to make copies
            for(CubePermutation tempPermutation: permutations) {
                tempPermutations.add(new CubePermutation(tempPermutation));
            }

            int item = values[i];

            int numPermutations = tempPermutations.size();

            for (int j = 0; j < numPermutations; j++) {
                tempPermutations.get(j).add(item);
            }

            result.addAll(tempPermutations);
        }

        return result;
    }

    public static ArrayList<Puzzle> getPossibleSolutions(Puzzle p, CubePermutation permutation)
    {
        // make a copy
        p = new Puzzle(p);

        ArrayList<Puzzle> solutions = new ArrayList<>();
        ArrayList<ArrayList<Cube>> tempSolutions = new ArrayList<>();

        ArrayList<Cube> cubes = p.getCubes();

        int numColumns = p.getNumberOfColumns();

        if (numColumns == 0) {
            return solutions;
        }

        int numCubes = cubes.size();

        for (int i = 0; i < numCubes; i++) {
            int value = permutation.getValue(i);
            Cube cube = cubes.get(i);
            int turns = 0;

            while (turns < numColumns) {
                if (cube.getValue(0) == value) {
                    tempSolutions = appendToPossibleSolutions(tempSolutions, i, cube);
                }

                cube.turnClockwise();

                turns++;
            }
        }

        for (ArrayList<Cube> temp : tempSolutions) {
            solutions.add(new Puzzle(temp));
        }

        return solutions;
    }

    private static ArrayList<ArrayList<Cube>> appendToPossibleSolutions(ArrayList<ArrayList<Cube>> solutions, int column, Cube cube)
    {
        assert column >= 0;

        // make a copy
        cube = new Cube(cube);

        if (solutions.isEmpty()) {
            ArrayList<Cube> solution = new ArrayList<>();
            solution.add(cube);
            solutions.add(0, solution);

            return solutions;
        }

        ArrayList<ArrayList<Cube>> result =  new ArrayList<>();
        int numSolutions = solutions.size();


        for (int i = 0; i < numSolutions; i++) {
            // make a copy
            ArrayList<Cube> solution = new ArrayList<>(solutions.get(i));

            if (column > (solution.size() - 1)) {
                // add the column
                solution.add(column, cube);
                result.add(solution);
                continue;
            }

            // make a copy and replace the column
            ArrayList<Cube> permutation = new ArrayList<>(solution);
            permutation.set(column, cube);

            // re-add the existing solution and add the new permutation
            result.add(solution);
            result.add(permutation);
        }

        return result;
    }

    public static boolean checkSolution(Puzzle p)
    {
        int numColumns = p.getNumberOfColumns();

        if (numColumns == 0) {
            return false;
        }

        ArrayList<Cube> cubes = p.getCubes();

        for (int i = 0; i < numColumns; i++) {
            CubePermutation f = new CubePermutation();

            for (Cube cube : cubes) {
                f.add(cube.getValue(i));
            }

            if (!hasCorrectSum(f)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasCorrectSum(CubePermutation f)
    {
        int sum = 0;

        for (int value : f.getValues()) {
            sum += value;
        }

        return sum == FACE_TOTAL;
    }
}
