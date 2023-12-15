class Day12 {
    fun solve1(lines: List<String>): Int {
        return lines.sumOf {
            val split = it.split(" ")
            val permutations = getPermutations(split[0])
            val encoding = split[1].split(",").map { v -> v.toInt() }
            permutations.filter { p -> isValid(p, encoding) }.size
        }
    }

    private fun isValid(gears: String, encoding: List<Int>): Boolean {
        val gearGroups = Regex("#+").findAll(gears).map { it.value.length }.toList()
        if (encoding.size != gearGroups.size) {
            return false
        }
        return encoding.indices.all { index ->
            encoding[index] == gearGroups[index]
        }
    }

    private fun getPermutations(line: String): List<String> {
        val permutations = mutableSetOf(line)
        var position = 0
        while (position < line.length) {
            val toConsider = permutations.filter { it[position] == '?' }.toSet()
            val toAdd = mutableSetOf<String>()
            toConsider.forEach { p ->
                toAdd.add("${p.substring(0, position)}.${p.substring(position + 1)}")
                toAdd.add("${p.substring(0, position)}#${p.substring(position + 1)}")
            }
            permutations.removeAll(toConsider)
            permutations.addAll(toAdd)
            position++
        }
        return permutations.toList()
    }

    fun solve2(lines: List<String>): Int {
        return 0
    }
}