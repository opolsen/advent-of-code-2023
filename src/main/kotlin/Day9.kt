class Day9 {
    fun solve1(lines: List<String>): Int {
        val extrapolatedValues = lines.map { line ->
            getSequences(line).map { it.last() }.reversed().reduce { acc, value -> acc + value }
        }
        return extrapolatedValues.sum()
    }

    fun solve2(lines: List<String>): Int {
        val extrapolatedValues = lines.map { line ->
            getSequences(line).map { it.first() }.reversed().reduce { acc, value -> value - acc }
        }
        return extrapolatedValues.sum()
    }

    private fun getSequences(line: String): List<List<Int>> {
        val history = line.split(" ").map { it.toInt() }
        val sequences = mutableListOf(history)
        var currentSequence = history
        while (currentSequence.any { it != 0 }) {
            currentSequence = currentSequence.indices.drop(1).map {
                currentSequence[it] - currentSequence[it - 1]
            }
            sequences.add(currentSequence)
        }
        return sequences
    }
}