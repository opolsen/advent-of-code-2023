fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("day_11_input.txt")?.bufferedReader()?.readLines()
    lines?.let {
        println("Day 11.1: ${Day11().solve1(lines)}")
        println("Day 11.2: ${Day11().solve2(lines, 1000000)}")
    }
}