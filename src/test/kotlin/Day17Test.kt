import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day17Test {
    @Test
    fun solve1() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent().lines()

        val solution = Day17().solve1(input)

        assertEquals(102, solution)
    }

    @Test
    fun solve1b() {
        val input = """
            2113411111323
            3211113515623
            3255245611154
            3446585845152
            4546657867116
            1438598798414
            4457876987716
            3637877979611
            4654967986881
            4564679986451
            1224686865511
            2546548887715
            4322674655511
        """.trimIndent().lines()

        val solution = Day17().solve1(input)

        assertEquals(28, solution)
    }

    @Test
    fun solve2() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent().lines()

        val solution = Day17().solve2(input)

        assertEquals(94, solution)
    }

    @Test
    fun solve2b() {
        val input = """
            111111111111
            999999999991
            999999999991
            999999999991
            999999999991
        """.trimIndent().lines()

        val solution = Day17().solve2(input)

        assertEquals(71, solution)
    }


}