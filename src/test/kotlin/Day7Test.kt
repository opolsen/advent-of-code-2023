import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day7Test {
    @Test
    fun testSolve1() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().lines()

        val solution = Day7().solve1(input)

        assertEquals(6440, solution)
    }

    @Test
    fun testSolve2() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().lines()

        val solution = Day7().solve2(input)

        assertEquals(5905, solution)
    }
}