import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {
    private val day3 = Day3()

    @Test
    fun testSolve1() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent().lines()

        val solution = day3.solve1(input)

        assertEquals(4361, solution)
    }

    @Test
    fun testSolve2() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent().lines()

        val solution = day3.solve2(input)

        assertEquals(467835, solution)
    }
}