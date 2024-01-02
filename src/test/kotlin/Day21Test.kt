import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day21Test {
    @Test
    fun testSolve1() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent().lines()

        val solution = Day21().solve1(input, 6)

        assertEquals(16, solution)
    }

    @Test
    fun testSolve2_10() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent().lines()

        val solution = Day21().solve2(input, 10)

        assertEquals(50, solution)

    }


    @Test
    fun testSolve2_100() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent().lines()

        val solution = Day21().solve2(input, 100)

        assertEquals(6536, solution)
    }

    @Test
    fun testSolve2_500() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent().lines()

        val solution = Day21().solve2(input, 500)

        assertEquals(167004, solution)
    }


    @Test
    fun testSolve2_5000() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent().lines()

        val solution = Day21().solve2(input, 5000)

        assertEquals(16733044, solution)
    }
}