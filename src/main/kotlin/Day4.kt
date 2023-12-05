import kotlin.math.pow

class Day4 {
    fun solve1(lines: List<String>): Double {
        var score = 0.0
        lines.forEach { line ->
            val matchingNumbers = getMatchingNumbers(line)
            if (matchingNumbers.isNotEmpty()) {
                score += 2.0.pow(matchingNumbers.size - 1.0)
            }
        }
        return score
    }

    fun solve2(lines: List<String>): Int {
        val copies = mutableMapOf<Int, Int>()
        lines.forEach { line ->
            val id = Regex("\\d+(?=:)").find(line)?.value?.toInt() ?: throw Exception("Did not find id")
            copies[id] = 1
        }

        lines.forEach { line ->
            val id = Regex("\\d+(?=:)").find(line)?.value?.toInt() ?: throw Exception("Did not find id")
            val matchingNumbers = getMatchingNumbers(line)
            for (i in id + 1..id + matchingNumbers.size) {
                copies[i] = (copies[i] ?: 1) + (copies[id] ?: 1)
            }
        }
        return copies.values.sum()
    }

    private fun getMatchingNumbers(card: String): Set<Int> {
        val winningNumbers = Regex("\\d+").findAll(card.split(":")[1].split("|")[0]).map { it.value.toInt() }
        val myNumbers = Regex("\\d+").findAll(card.split(":")[1].split("|")[1]).map { it.value.toInt() }
        return winningNumbers.toSet().intersect(myNumbers.toSet())
    }
}