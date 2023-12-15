import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day13Test {
    @Test
    fun testSolve1Vertical() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
        """.trimIndent().lines()
        val solution = Day13().solve1(input)
        assertEquals(5, solution)
    }

    @Test
    fun testSolve1Horizontal() {
        val input = """
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent().lines()
        val solution = Day13().solve1(input)
        assertEquals(400, solution)
    }

    @Test
    fun testSolve1() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent().lines()

        val solution = Day13().solve1(input)
        assertEquals(405, solution)
    }

    @Test
    fun testSolve2() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent().lines()

        val solution = Day13().solve2(input)
        assertEquals(400, solution)
    }
}