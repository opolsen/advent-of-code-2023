fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("day_15_input.txt")?.bufferedReader()?.readLines()
    lines?.let {
        println("Day 15.1: ${Day15().solve1(lines.first())}")
        println("Day 15.2: ${Day15().solve2(lines.first())}")
    }
}