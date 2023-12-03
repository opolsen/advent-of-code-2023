fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("day_1_input.txt")?.bufferedReader()?.readLines()
    lines?.let {
        println("Day 1.1: ${Day1().solve1(lines)}")
        println("Day 1.2: ${Day1().solve2(lines)}")
    }

    val day2Lines = object {}.javaClass.getResourceAsStream("day_2_input.txt")?.bufferedReader()?.readLines()
    day2Lines?.let {
        println("Day 2.1: ${Day2().solve1(day2Lines)}")
        println("Day 2.2: ${Day2().solve2(day2Lines)}")
    }

    val day3Lines = object {}.javaClass.getResourceAsStream("day_3_input.txt")?.bufferedReader()?.readLines()
    day3Lines?.let {
        println("Day 3.1: ${Day3().solve1(day3Lines)}")
        println("Day 3.2: ${Day3().solve2(day3Lines)}")
    }
}