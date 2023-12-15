class Day13 {
    fun solve1(lines: List<String>): Int {
        val mirrors = getMirrors(lines)
        val horizontalSum = mirrors.mapNotNull { findHorizontalReflection(it) }.sumOf { it * 100 }
        val verticalSum = mirrors.mapNotNull { findVerticalReflection(it) }.sum()
        return horizontalSum + verticalSum
    }

    fun solve2(lines: List<String>): Int {
        val mirrors = getMirrors(lines)
        val fixedValues = mirrors.map { getFixedValue(it) }
        val horizontalSum = fixedValues.mapNotNull { it.second }.sumOf { it * 100 }
        val verticalSum = fixedValues.mapNotNull { it.first }.sum()
        return horizontalSum + verticalSum
    }

    private fun getMirrors(lines: List<String>): MutableList<List<String>> {
        val mirrors = mutableListOf<List<String>>()
        var position = 0
        while (position < lines.size) {
            val mirror = lines.subList(position, lines.size).takeWhile { it.isNotEmpty() }
            mirrors.add(mirror)
            position += mirror.size + 1
        }
        return mirrors
    }

    private fun findHorizontalReflection(mirror: List<String>, ignore: Int? = null): Int? {
        for (i in 0..mirror.size-2) {
            if (mirror[i] == mirror[i+1]) {
                var isMirror = true
                for (j in 0..minOf(i, mirror.size-2-i)) {
                    if (mirror[i-j] != mirror[i+1+j]) {
                        isMirror = false
                    }
                }
                if (isMirror && ignore != i + 1) {
                    return i + 1
                }
            }
        }
        return null
    }

    private fun findVerticalReflection(mirror: List<String>, ignore: Int? = null): Int? {
        for (i in 0..mirror[0].length-2) {
            if (columnToString(mirror, i) == columnToString(mirror, i+1)) {
                var isMirror = true
                val range = 0..minOf(i, mirror[0].length-2-i)
                for (j in range) {
                    val a = columnToString(mirror, i-j)
                    val b = columnToString(mirror, i+1+j)
                    if (a != b) {
                        isMirror = false
                    }
                }
                if (isMirror && ignore != i + 1) {
                    return i + 1
                }
            }
        }
        return null
    }

    private fun columnToString(lines: List<String>, index: Int): String {
        return lines.map { it[index] }.joinToString("")
    }

    private fun getFixedValue(mirror: List<String>): Pair<Int?, Int?> {
        val existingHorizontal = findHorizontalReflection(mirror)
        val existingVertical = findVerticalReflection(mirror)
        for (i in mirror.indices) {
            for (j in mirror[i].indices) {
                val fixedMirror = fixSmudgeAt(mirror, j, i)
                val vertical = findVerticalReflection(fixedMirror, existingVertical)
                val horizontal = findHorizontalReflection(fixedMirror, existingHorizontal)
                if (vertical != null || horizontal != null) {
                    return Pair(vertical, horizontal)
                }
            }
        }
        throw Error("Could not find any fixed reflextions")
    }

    private fun fixSmudgeAt(mirror: List<String>, x: Int, y: Int): List<String> {
        return mirror.mapIndexed { i, line ->
            if (i == y) {
                line.mapIndexed { i2, char ->
                    if (i2 == x) {
                        fixSmudge(char)
                    } else {
                        char
                    }
                }.joinToString("")
            } else {
                line
            }
        }
    }

    private fun fixSmudge(char: Char): Char {
        return when(char) {
            '.' -> '#'
            '#' -> '.'
            else -> throw Exception("Unknown char: $char")
        }
    }

}