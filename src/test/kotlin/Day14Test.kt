import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day14Test {
    @Test
    fun testSolve1() {
        val input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent().lines()

        val solution = Day14().solve1(input)

        assertEquals(136, solution)
    }
}