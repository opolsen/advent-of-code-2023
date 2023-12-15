import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day12Test {
    @Test
    fun solve1a() {
        val input = """
            ???.### 1,1,3
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(1, solution)
    }

    @Test
    fun solve1b() {
        val input = """
            .??..??...?##. 1,1,3
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(4, solution)
    }

    @Test
    fun solve1c() {
        val input = """
            ?#?#?#?#?#?#?#? 1,3,1,6
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(1, solution)
    }

    @Test
    fun solve1d() {
        val input = """
            ????.#...#... 4,1,1
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(1, solution)
    }

    @Test
    fun solve1e() {
        val input = """
            ????.######..#####. 1,6,5
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(4, solution)
    }

    @Test
    fun solve1f() {
        val input = """
            ?###???????? 3,2,1
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(10, solution)
    }

    @Test
    fun solve1() {
        val input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent().lines()
        val solution = Day12().solve1(input)
        assertEquals(21, solution)
    }

    @Test
    fun solve2a() {
        val input = """
            ???.### 1,1,3
        """.trimIndent().lines()
        val solution = Day12().solve2(input)
        assertEquals(1, solution)
    }

    @Test
    fun solve2b() {
        val input = """
            .??..??...?##. 1,1,3
        """.trimIndent().lines()
        val solution = Day12().solve2(input)
        assertEquals(16384, solution)
    }

    @Test
    fun solve2c() {
        val input = """
            ?#?#?#?#?#?#?#? 1,3,1,6
        """.trimIndent().lines()
        val solution = Day12().solve2(input)
        assertEquals(1, solution)
    }

    @Test
    fun solve2d() {
        val input = """
            ????.#...#... 4,1,1
        """.trimIndent().lines()
        val solution = Day12().solve2(input)
        assertEquals(16, solution)
    }

    @Test
    fun solve2e() {
        val input = """
            ????.######..#####. 1,6,5
        """.trimIndent().lines()
        val solution = Day12().solve2(input)
        assertEquals(2500, solution)
    }

    @Test
    fun solve2f() {
        val input = """
            ?###???????? 3,2,1
        """.trimIndent().lines()
        val solution = Day12().solve2(input)
        assertEquals(506250, solution)
    }
}