// Washington monument puzzle

// all cube faces must add up to 555
// there is over 262000+ possible permutations

// http://www.creativecrafthouse.com/index.php?main_page=product_info&products_id=502

import java.util.ArrayList;

class Main
{
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

        Puzzle puzzle = new Puzzle(cubes);

        ArrayList<Puzzle> correctSolutions = Puzzle.solve(puzzle);

        for (Puzzle solution : correctSolutions) {
            System.out.print(solution + "\n\n");
        }

        System.out.println(String.format("Found %d possible solutions", correctSolutions.size()));
    }
}