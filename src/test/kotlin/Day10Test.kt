import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun testSolve1() {
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

    @Test
    fun testSolve2a() {
        val input = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
        """.trimIndent().lines()
        val solution = Day10().solve2(input)
        assertEquals(4, solution)
    }

    @Test
    fun testSolve2aWithSqueeze() {
        val input = """
            ..........
            .S------7.
            .|F----7|.
            .||....||.
            .||....||.
            .|L-7F-J|.
            .|..||..|.
            .L--JL--J.
            ..........
        """.trimIndent().lines()
        val solution = Day10().solve2(input)
        assertEquals(4, solution)
    }

    @Test
    fun testSolve2b() {
        val input = """
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
        """.trimIndent().lines()
        val solution = Day10().solve2(input)
        assertEquals(8, solution)
    }

    @Test
    fun testSolve2c() {
        val input = """
            FF7FSF7F7F7F7F7F---7
            L|LJ||||||||||||F--J
            FL-7LJLJ||||||LJL-77
            F--JF--7||LJLJ7F7FJ-
            L---JF-JLJ.||-FJLJJ7
            |F|F-JF---7F7-L7L|7|
            |FFJF7L7F-JF7|JL---7
            7-L-JL7||F7|L7F-7F7|
            L.L7LFJ|||||FJL7||LJ
            L7JLJL-JLJLJL--JLJ.L
        """.trimIndent().lines()
        val solution = Day10().solve2(input)
        assertEquals(10, solution)
    }
}