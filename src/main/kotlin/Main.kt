fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("day_9_input.txt")?.bufferedReader()?.readLines()
    lines?.let {
        println("Day 9.1: ${Day9().solve1(lines)}")
        println("Day 9.2: ${Day9().solve2(lines)}")
    }
}