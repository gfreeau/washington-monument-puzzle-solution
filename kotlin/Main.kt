// Washington monument puzzle

// all cube faces must add up to 555
// there is over 262000+ possible permutations

// http://www.creativecrafthouse.com/index.php?main_page=product_info&products_id=502

fun main(args: Array<String>) {
    val cubes = arrayOf(
        intArrayOf(95, 30, 25, 60),
        intArrayOf(55, 25, 20, 70),
        intArrayOf(20, 50, 55, 10),
        intArrayOf(45, 55, 65, 15),
        intArrayOf(90, 55, 90, 80),
        intArrayOf(60, 90, 85, 55),
        intArrayOf(45, 95, 15, 55),
        intArrayOf(75, 65, 55, 55),
        intArrayOf(55, 70, 35, 55),
        intArrayOf(55, 55, 60, 75)
    )

    val puzzle = Puzzle(cubes)

    val correctSolutions = Puzzle.solve(puzzle)

    for (solution in correctSolutions) {
        print(solution.toString() + "\n\n")
    }

    println("Found ${correctSolutions.size} possible solutions")
}