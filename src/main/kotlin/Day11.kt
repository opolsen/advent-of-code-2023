import kotlin.math.absoluteValue

class Day11 {
    fun solve1(lines: List<String>): Long {
        return getGalaxyDistances(lines, 2).sum()
    }

    fun solve2(lines: List<String>, expansionSize: Int): Long {
        return getGalaxyDistances(lines, expansionSize).sum()
    }

    private fun getGalaxyDistances(lines: List<String>, expansionSize: Int): List<Long> {
        val xExpansions = lines[0].indices.filter { lines.all { line -> line[it] == '.' } }
        val yExpansions = lines.indices.filter { lines[it].all { c -> c == '.' } }

        val expandedGalaxyLocations = galaxyLocations(lines).map { location ->
            Pair(
                location.first + (xExpansions.filter { it < location.first }.size * (expansionSize - 1)),
                location.second + (yExpansions.filter { it < location.second }.size * (expansionSize - 1)),
            )
        }

        return locationPairs(expandedGalaxyLocations).map { distance(it.first, it.second) }
    }

    private fun galaxyLocations(lines: List<String>): List<Pair<Int, Int>> {
        val galaxyLocations = mutableListOf<Pair<Int, Int>>()
        lines.indices.forEach { y ->
            lines[y].indices.forEach { x ->
                if (lines[y][x] == '#') {
                    galaxyLocations.add(Pair(x, y))
                }
            }
        }
        return galaxyLocations.toList()
    }

    private fun locationPairs(locations: List<Pair<Int, Int>>): Set<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        val pairs = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        locations.forEach { l1 ->
            locations.forEach { l2 ->
                if (l1 != l2 && Pair(l2, l1) !in pairs) {
                    pairs.add(Pair(l1, l2))
                }
            }
        }
        return pairs.toSet()
    }

    private fun distance(a: Pair<Int, Int>, b: Pair<Int, Int>): Long {
        return (b.first - a.first).absoluteValue.toLong() + (b.second - a.second).absoluteValue.toLong()
    }
}