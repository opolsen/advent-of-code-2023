class Day3 {
    fun solve1(lines: List<String>): Int {
        val partNumbers = mutableListOf<Int>()
        val lineToSymbolIndices = fineSymbolIndicesPerLine(lines)
        for (i in lines.indices) {
            val numberMatches = Regex("\\d+").findAll(lines[i])
            numberMatches.forEach {
                if (hasAdjacentSymbol(i, it.range, lineToSymbolIndices)) {
                    partNumbers.add(it.value.toInt())
                }
            }
        }
        return partNumbers.sum()
    }

    private fun fineSymbolIndicesPerLine(lines: List<String>): MutableMap<Int, MutableList<Int>> {
        val lineToSymbolIndices = mutableMapOf<Int, MutableList<Int>>()
        for (i in lines.indices) {
            val indices = mutableListOf<Int>()
            for (j in lines[i].indices) {
                if (!lines[i][j].isLetterOrDigit() && lines[i][j] != '.') {
                    indices.add(j)
                }
            }
            lineToSymbolIndices[i] = indices
        }
        return lineToSymbolIndices
    }

    private fun hasAdjacentSymbol(index: Int, range: IntRange, lineToSymbolIndices: Map<Int, List<Int>>): Boolean {
        val hasAbove = range.any {
            lineToSymbolIndices[index - 1]?.contains(it) ?: false
        }
        val hasBelow = range.any {
            lineToSymbolIndices[index + 1]?.contains(it) ?: false
        }
        for (i in index - 1..index + 1) {
            lineToSymbolIndices[i]?.let {
                if (it.contains(range.first - 1) || it.contains(range.last + 1)) {
                    return true
                }
            }
        }
        return hasAbove || hasBelow
    }

    fun solve2(lines: List<String>): Int {
        val lineToSymbolIndices = fineSymbolIndicesPerLine(lines)
        val lineToPartMatch = mutableMapOf<Int, MutableList<MatchResult>>()
        for (i in lines.indices) {
            val partRanges = mutableListOf<MatchResult>()
            val numberMatches = Regex("\\d+").findAll(lines[i])
            numberMatches.forEach {
                if (hasAdjacentSymbol(i, it.range, lineToSymbolIndices)) {
                    partRanges.add(it)
                }
            }
            lineToPartMatch[i] = partRanges
        }

        val gearRatios = mutableListOf<Int>()
        for (i in lines.indices) {
            for (j in lines[i].indices) {
                if (lines[i][j] == '*') {
                    val adjacentMatches = getAdjacentMatches(i, j, lineToPartMatch)
                    if (adjacentMatches.size == 2) {
                        gearRatios.add(adjacentMatches.map { it.value.toInt() }.reduce { acc, x -> acc * x })
                    }
                }
            }
        }

        return gearRatios.sum()
    }

    private fun getAdjacentMatches(i: Int, j: Int, lineToPartMatch: Map<Int, List<MatchResult>>): List<MatchResult> {
        val matches = mutableSetOf<MatchResult>()
        for (i2 in i - 1..i + 1) {
            for (j2 in j - 1..j + 1) {
                lineToPartMatch[i2]?.let { partMatchesForLine ->
                    partMatchesForLine.forEach { partMatch ->
                        if (j2 in partMatch.range) {
                            matches.add(partMatch)
                        }
                    }
                }
            }
        }
        return matches.toList()
    }
}