// Washington monument puzzle

// all cube faces must add up to 555
// there is over 262000+ possible permutations

// http://www.creativecrafthouse.com/index.php?main_page=product_info&products_id=502

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solve
{
    static final int FACE_TOTAL = 555;

    public static void main(String[] args)
    {
        int[][] cubes = {
            {95, 30, 25, 60},
            {55, 25, 20, 70},
            {20, 50, 55, 10},
            {45, 55, 65, 15},
            {90, 55, 90, 80},
            {60, 90, 85, 55},
            {45, 95, 15, 55},
            {75, 65, 55, 55},
            {55, 70, 35, 55},
            {55, 55, 60, 75},
        };

        ArrayList<ArrayList<Integer>> face1Permutations = getCubePermutations(cubes, 0);
        face1Permutations.removeIf(cubeFaces -> !hasCorrectSum(cubeFaces));

        HashMap<Integer, ArrayList<int[]>> correctSolutions = new HashMap<>();

        for (ArrayList<Integer> permutation : face1Permutations) {
            ArrayList<ArrayList<int[]>> possibleSolutions = getPossibleSolutions(permutation, cubes);

            for (ArrayList<int[]> solution : possibleSolutions) {
                if (checkSolution(solution)) {
                    Integer solutionHash = getSolutionHash(solution);

                    if (correctSolutions.containsKey(solutionHash)) {
                        continue;
                    }

                    correctSolutions.put(getSolutionHash(solution), solution);
                    System.out.print(getCubeOutput(solution) + "\n\n");
                }
            }
        }

        System.out.println(String.format("Found %d possible solutions", correctSolutions.size()));
    }

    static ArrayList<ArrayList<Integer>> getCubePermutations(int[][] cubes, int face)
    {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();

        int numCubes = cubes.length;

        int[] values;

        for (int i = 0; i < numCubes; i++) {
            if (i == 0) {
                values = new int[] { cubes[0][face] };
            } else {
                values = cubes[i];
            }

            permutations = appendCubePermutations(permutations, values);
        }

        return permutations;
    }

    static ArrayList<ArrayList<Integer>> appendCubePermutations(ArrayList<ArrayList<Integer>> permutations, int[] values)
    {
        if (permutations.isEmpty()) {
            for (int value : values) {
                permutations.add(new ArrayList<Integer>() {{
                    add(value);
                }});
            }

            return permutations;
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            ArrayList<ArrayList<Integer>> tempPermutations = new ArrayList<>();

            // need to make copies
            for(ArrayList<Integer> tempPermutation: permutations) {
                tempPermutations.add(new ArrayList<>(tempPermutation));
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

    static boolean hasCorrectSum(ArrayList<Integer> cubeFaces)
    {
        int sum = 0;

        for (int value : cubeFaces) {
            sum += value;
        }

        return sum == FACE_TOTAL;
    }

    static ArrayList<ArrayList<int[]>> getPossibleSolutions(ArrayList<Integer> permutation, int[][] cubes)
    {
        cubes = copyCubes(cubes);

        ArrayList<ArrayList<int[]>> solutions = new ArrayList<>();

        if (cubes.length == 0) {
            return solutions;
        }

        int numCubes = cubes.length;
        int numFaces = cubes[0].length;

        for (int i = 0; i < numCubes; i++) {
            int value = permutation.get(i);
            int turns = 0;

            while (turns < numFaces) {
                if (cubes[i][0] == value) {
                    solutions = appendToPossibleSolutions(solutions, i, cubes[i]);
                }

                turnCubeClockwise(cubes[i]);

                turns++;
            }
        }

        return solutions;
    }

    static ArrayList<ArrayList<int[]>> appendToPossibleSolutions(ArrayList<ArrayList<int[]>> solutions, int column, int[] cube)
    {
        cube = Arrays.copyOf(cube, cube.length);

        if (solutions.isEmpty()) {
            ArrayList<int[]> solution = new ArrayList<>();
            solution.add(cube);
            solutions.add(0, solution);


            return solutions;
        }

        ArrayList<ArrayList<int[]>> result =  new ArrayList<>();
        int numSolutions = solutions.size();


        for (int i = 0; i < numSolutions; i++) {
            // make a copy
            ArrayList<int[]> solution = new ArrayList<>(solutions.get(i));

            if (column > (solution.size() - 1)) {
                // add the column
                solution.add(column, cube);
                result.add(solution);
                continue;
            }

            // make a copyCubes and replace the column
            ArrayList<int[]> permutation = new ArrayList<>(solution);
            permutation.set(column, cube);

            // re-add the existing solution and add the new permutation
            result.add(solution);
            result.add(permutation);
        }

        return result;
    }

    static void turnCubeClockwise(int[] cube)
    {
        int last = cube[cube.length - 1];

        for (int i = cube.length - 2; i >= 0; i--) {
            cube[i+1] = cube[i];
        }

        cube[0] = last;
    }

    static boolean checkSolution(ArrayList<int[]> cubes)
    {
        if (cubes.size() == 0) {
            return false;
        }

        int numFaces = cubes.get(0).length;

        for (int i = 0; i < numFaces; i++) {
            ArrayList<Integer> cubeFace = new ArrayList<>();

            for (int[] cube : cubes) {
                cubeFace.add(cube[i]);
            }

            if (!hasCorrectSum(cubeFace)) {
                return false;
            }
        }

        return true;
    }

    public static int[][] copyCubes(int[][] src)
    {
        int[][] dst = new int[src.length][];

        for (int i = 0; i < src.length; i++) {
            dst[i] = Arrays.copyOf(src[i], src[i].length);
        }

        return dst;
    }

    public static String getCubeOutput(ArrayList<int[]> cubes) {
        String output = "";

        for (int[] cube : cubes) {
            output += Arrays.toString(cube) + "\n";
        }

        return output;
    }

    public static int getSolutionHash(ArrayList<int[]> cubes) {
        String s = "";

        for (int[] cube : cubes) {
            s += Arrays.toString(cube);
        }

        return s.hashCode();
    }
}