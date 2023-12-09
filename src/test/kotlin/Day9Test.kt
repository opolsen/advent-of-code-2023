import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun testSolve1() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().lines()

        val solution = Day9().solve1(input)

        assertEquals(114, solution)
    }

    @Test
    fun testSolve2() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().lines()

        val solution = Day9().solve2(input)

        assertEquals(2, solution)
    }
}