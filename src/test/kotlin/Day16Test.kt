import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day16Test {
    @Test
    fun solve1() {
        val input = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """.trimIndent().lines()

        val solution = Day16().solve1(input)

        assertEquals(46, solution)
    }

    @Test
    fun solve2() {
        val input = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """.trimIndent().lines()

        val solution = Day16().solve2(input)

        assertEquals(51, solution)
    }

}