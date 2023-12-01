import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    private val day1 = Day1()

    @Test
    fun testSolve1() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()

        val solution = day1.solve1(input)

        assertEquals(142, solution)
    }

    @Test
    fun testSolve2() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent().lines()

        val solution = day1.solve2(input)

        assertEquals(281, solution)
    }

    @Test
    fun `should parse overlapping text digits`() {
        val input = "oneight"

        val solution = day1.solve2(listOf(input))

        assertEquals(18, solution)
    }
}