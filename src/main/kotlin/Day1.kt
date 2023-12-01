class Day1 {
    private val textToDigit = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun solve1(lines: List<String>): Int {
        return lines.sumOf { getCalibrationValue(it) }
    }

    fun solve2(lines: List<String>): Int {
        return lines.sumOf { getCalibrationValue(convertTextDigits(it)) }
    }

    private fun convertTextDigits(line: String): String {
        var convertedLine = ""
        for (i in line.indices) {
            if (line[i].isDigit()) {
                convertedLine += line[i]
            }
            textToDigit.keys.firstOrNull { line.substring(i).startsWith(it) }?.let {
                convertedLine += textToDigit[it]
            }
        }
        return convertedLine
    }

    private fun getCalibrationValue(line: String): Int {
        val digits = line.filter { it.isDigit() }
        return Integer.parseInt("${digits.first()}${digits.last()}")
    }
}