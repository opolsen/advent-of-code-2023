fun main(args: Array<String>) {
    val day8Lines = object {}.javaClass.getResourceAsStream("day_8_input.txt")?.bufferedReader()?.readLines()
    day8Lines?.let {
        println("Day 8.1: ${Day8().solve1(day8Lines)}")
        println("Day 8.2: ${Day8().solve2(day8Lines)}")
    }
}