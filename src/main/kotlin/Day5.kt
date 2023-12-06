import java.math.BigInteger

class Day5 {
    fun solve1(lines: List<String>): Long {
        val seeds = Regex("\\d+").findAll(lines[0]).map { it.value.toLong() }.toList()
        val mapIndices = lines.indices.filter {
            lines[it].contains("map")
        }
        var mappedValues = seeds
        mapIndices.forEach {
            val mapLines = lines.subList(it + 1, lines.size - 1).takeWhile { l ->
                l.contains(Regex("\\d+"))
            }
            val mapValues = mapLines.map { l ->
                Regex("\\d+").findAll(l).map { l2 -> l2.value.toLong() }.toList()
            }
            mappedValues = mappedValues.map { v -> getMappedValue(v, mapValues) }
        }
        return mappedValues[mappedValues.indexOf(mappedValues.min())]
    }

    fun solve2(lines: List<String>): Long {
        val seedValues = Regex("\\d+").findAll(lines[0]).map { it.value.toLong() }.toList()
        val seedRanges = mutableMapOf<Long, Long>()
        seedValues.indices.forEach { i ->
            if (i.mod(2) == 0) {
                seedRanges[seedValues[i]] = seedValues[i + 1]
            }
        }
        var minLocation = -1L
        val mapIndices = lines.indices.filter {
            lines[it].contains("map")
        }
        val maps = mutableListOf<List<List<Long>>>()
        mapIndices.forEach {
            val mapLines = lines.subList(it + 1, lines.size - 1).takeWhile { l ->
                l.contains(Regex("\\d+"))
            }
            maps.add(mapLines.map { l -> Regex("\\d+").findAll(l).map { l2 -> l2.value.toLong() }.toList() })
        }
        var rangeIndex = 0
        seedRanges.entries.forEach { (start, length) ->
            println("started rangeIndex: $rangeIndex")
            rangeIndex++
            var currSeed = start
            while (currSeed < start + length) {
                var mappedValue = currSeed
                maps.forEach { m ->
                    mappedValue = getMappedValue(mappedValue, m)
                }
                if (minLocation < 0 || mappedValue < minLocation) {
                    minLocation = mappedValue
                }
                currSeed++
            }
        }
        return minLocation
    }

    private fun getMappedValue(source: Long, mapValues: List<List<Long>>): Long {
        mapValues.forEach {
            val mapDest = it[0]
            val mapSource = it[1]
            val mapLength = it[2]
            var indexOf = -1L
            if (source >= mapSource && source < mapSource + mapLength) {
                indexOf = source - mapSource
            }
            if (indexOf >= 0) {
                return mapDest + indexOf
            }
        }
        return source
    }

}