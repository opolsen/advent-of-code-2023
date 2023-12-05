import java.math.BigInteger

class Day5 {
    fun solve1(lines: List<String>): BigInteger {
        val seeds = Regex("\\d+").findAll(lines[0]).map { it.value.toBigInteger() }.toList()
        val mapIndices = lines.indices.filter {
            lines[it].contains("map")
        }
        var mappedValues = seeds
        mapIndices.forEach {
            val mapLines = lines.subList(it + 1, lines.size - 1).takeWhile { l ->
                l.contains(Regex("\\d+"))
            }
            val mapValues = mapLines.map { l ->
                Regex("\\d+").findAll(l).map { l2 -> l2.value.toBigInteger() }.toList()
            }
            mappedValues = mappedValues.map { v -> getMappedValue(v, mapValues) }
        }
        return mappedValues[mappedValues.indexOf(mappedValues.min())]
    }

    fun solve2(lines: List<String>): BigInteger {
        val seedValues = Regex("\\d+").findAll(lines[0]).map { it.value.toBigInteger() }.toList()
        val seedRanges = mutableMapOf<BigInteger, BigInteger>()
        seedValues.indices.forEach { i ->
            if (i.mod(2) == 0) {
                seedRanges[seedValues[i]] = seedValues[i + 1]
            }
        }
        var minLocation = BigInteger.valueOf(-1)
        val mapIndices = lines.indices.filter {
            lines[it].contains("map")
        }
        val maps = mutableListOf<List<List<BigInteger>>>()
        mapIndices.forEach {
            val mapLines = lines.subList(it + 1, lines.size - 1).takeWhile { l ->
                l.contains(Regex("\\d+"))
            }
            maps.add(mapLines.map { l -> Regex("\\d+").findAll(l).map { l2 -> l2.value.toBigInteger() }.toList() })
        }
        var rangeIndex = 0
        seedRanges.entries.forEach { (start, length) ->
            rangeIndex++
            var currSeed = start
            while (currSeed < start + length) {
                var mappedValue = currSeed
                maps.forEach { m ->
                    mappedValue = getMappedValue(mappedValue, m)
                }
                if (minLocation < BigInteger.ZERO || mappedValue < minLocation) {
                    minLocation = mappedValue
                }
                currSeed++
            }
        }
        return minLocation
    }

    private fun getMappedValue(source: BigInteger, mapValues: List<List<BigInteger>>): BigInteger {
        mapValues.forEach {
            val mapDest = it[0]
            val mapSource = it[1]
            val mapLength = it[2]
            var indexOf = BigInteger.valueOf(-1)
            if (source >= mapSource && source < mapSource + mapLength) {
                indexOf = source - mapSource
            }
            if (indexOf >= BigInteger.valueOf(0)) {
                return mapDest + indexOf
            }
        }
        return source
    }

}