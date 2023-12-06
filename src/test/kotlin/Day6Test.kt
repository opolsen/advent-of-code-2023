import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {
    @Test
    fun testSolve1() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

        val solution = Day6().solve1(input)

        assertEquals(288, solution)
    }

    @Test
    fun testSolve2() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

        val solution = Day6().solve2(input)

        assertEquals(71503, solution)
    }
}