fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("day_1_input.txt")?.bufferedReader()?.readLines()
    lines?.let {
        println("Day 1.1: ${Day1().solve1(lines)}")
        println("Day 1.2: ${Day1().solve2(lines)}")
    }
}