import kotlin.math.pow

class Day7 {
    private val cardValue = mapOf(
        'T' to 10,
        'J' to 11,
        'Q' to 12,
        'K' to 13,
        'A' to 14,
    )

    fun solve1(lines: List<String>): Int {
        val pairs = lines.map {
            val split = it.split(" ")
            Pair(split[0], split[1].toInt())
        }
        return pairs.sortedBy { getScore(it.first) }
            .onEach { println(it) }
            .map { it.second }
            .reduceIndexed { index, acc, bid -> acc + (index + 1) * bid }
    }

    fun solve2(lines: List<String>): Int {
        val pairs = lines.map {
            val split = it.split(" ")
            Pair(split[0], split[1].toInt())
        }
        return pairs.sortedBy { getScore(it.first, useJoker = true) }
            .onEach { println(it) }
            .map { it.second }.reduceIndexed { index, acc, bid -> acc + (index + 1) * bid }
    }

    private fun getScore(hand: String, useJoker: Boolean = false): Double {
        val cards = hand.toCharArray()
        val jokers = cards.filter { it == 'J' }.size
        val counts = cards.groupBy { it }.mapValues { (key, value) ->
            if (useJoker) {
                if (key == 'J') {
                    0
                } else {
                    value.size + jokers
                }
            } else {
                value.size
            }
        }
        var score = 0.0
        val typeBaseScore: Long = 10000000
        score += typeBaseScore * when {
            counts.containsValue(5) || jokers == 5 -> 7
            counts.containsValue(4) -> 6
            (!useJoker || jokers == 0) && counts.containsValue(3) && counts.containsValue(2) || useJoker && counts.values.filter { it == 3 }.size == 2 -> 5
            counts.containsValue(3) -> 4
            (!useJoker || jokers == 0) && counts.values.filter { it == 2 }.size == 2 -> 3
            counts.containsValue(2) -> 2
            else -> 1
        }
        cards.forEachIndexed { index, c ->
            score += if (c.isDigit()) {
                14.0.pow(cards.size - 1 - index) * c.digitToInt()
            } else {
                14.0.pow(cards.size - 1 - index) * (if (useJoker && c == 'J') 1 else cardValue[c]
                    ?: throw Exception("Could not find value for card $c"))
            }
        }
        return score
    }
}