import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun testSolve1() {
        val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        val solution = Day15().solve1(input)
        assertEquals(1320, solution)
    }

    @Test
    fun testSolve2() {
        val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        val solution = Day15().solve2(input)
        assertEquals(145, solution)
    }

}