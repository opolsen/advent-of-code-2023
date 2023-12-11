import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun testSolve1a() {
        val input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent().lines()
        val solution = Day10().solve1(input)
        assertEquals(4, solution)
    }

    @Test
    fun testSolve1b() {
        val input = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """.trimIndent().lines()
        val solution = Day10().solve1(input)
        assertEquals(8, solution)
    }

    @Test
    fun testSolve1c() {
        val input = """
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ
        """.trimIndent().lines()
        val solution = Day10().solve1(input)
        assertEquals(8, solution)
    }
}