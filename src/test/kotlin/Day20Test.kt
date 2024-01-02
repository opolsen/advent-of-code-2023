import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day20Test {
    @Test
    fun testSolve1a_1() {
        val input = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
        """.trimIndent().lines()

        val solution = Day20().solve1(input, 1)

        assertEquals(32, solution)
    }

    @Test
    fun testSolve1a_1000() {
        val input = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
        """.trimIndent().lines()

        val solution = Day20().solve1(input, 1000)

        assertEquals(32000000, solution)
    }

    @Test
    fun testSolve1b_1() {
        val input = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
        """.trimIndent().lines()

        val solution = Day20().solve1(input, 1)

        assertEquals(16, solution)
    }


    @Test
    fun testSolve1b_1000() {
        val input = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
        """.trimIndent().lines()

        val solution = Day20().solve1(input, 1000)

        assertEquals(11687500, solution)
    }

}