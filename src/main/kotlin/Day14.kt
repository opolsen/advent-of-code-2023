class Day14 {
    fun solve1(lines: List<String>): Int {
        val rockPositions = getRockPositions(lines)
        return rockPositions.sumOf {
            var sum = 0
            val y = it.first.second + 1
            val count = it.second
            for (i in y..<y + count) {
                sum += lines.size - i
            }
            sum
        }
    }

    private fun getRockPositions(lines: List<String>): List<Pair<Pair<Int, Int>, Int>> {
        val rockPositions = mutableListOf<Pair<Pair<Int, Int>,Int>>()
        lines[0].indices.forEach { x ->
            val column = lines.map { it[x] }.joinToString("")
            val split = column.split("#")
            var rockPos = -1
            split.forEach { s ->
                val rocks = s.filter { c -> c == 'O' }.length
                if (rocks > 0) {
                    rockPositions.add(Pair(Pair(x, rockPos), rocks))
                }
                rockPos += s.length + 1
            }
        }
        return rockPositions
    }
}