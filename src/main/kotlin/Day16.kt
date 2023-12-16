class Day16 {
    fun solve1(lines: List<String>): Int {
        val initialBeam = Beam(Pair(0, 0), Direction.East)
        return getEnergizedTiles(lines, initialBeam).size
    }

    fun solve2(lines: List<String>): Int {
        val initialBeams = mutableListOf<Beam>()
        lines.indices.forEach { y ->
            initialBeams.add(Beam(Pair(0, y), Direction.East))
            initialBeams.add(Beam(Pair(lines.size - 1, y), Direction.West))
        }
        lines[0].indices.forEach { x ->
            initialBeams.add(Beam(Pair(x, 0), Direction.South))
            initialBeams.add(Beam(Pair(x, lines[0].count() - 1), Direction.North))
        }
        return initialBeams.maxOf { getEnergizedTiles(lines, it).size }
    }

    private fun getEnergizedTiles(lines: List<String>, initialBeam: Beam): Set<Pair<Int, Int>> {
        val beamQueue = ArrayDeque(setOf(initialBeam))
        val seenBeams = mutableSetOf<Beam>()
        while (beamQueue.isNotEmpty()) {
            val currentBeam = beamQueue.removeFirst()
            seenBeams.add(currentBeam)
            val nextBeams = currentBeam.next(lines[currentBeam.pos.second][currentBeam.pos.first])
            nextBeams.forEach { b ->
                if (b !in seenBeams && b.pos.first in lines[0].indices && b.pos.second in lines.indices) {
                    beamQueue.add(b)
                }
            }
        }
        return seenBeams.map { it.pos }.toSet()
    }
}

private enum class Direction(val x: Int, val y: Int) {
    North(0, -1), East(1, 0), South(0, 1), West(-1, 0)
}

private data class Beam(val pos: Pair<Int, Int>, val direction: Direction)

private fun Beam.next(c: Char): Set<Beam> {
    return when (c) {
        '.' -> setOf(move(direction))
        '/' -> {
            when (direction) {
                Direction.East -> setOf(move(Direction.North))
                Direction.West -> setOf(move(Direction.South))
                Direction.North -> setOf(move(Direction.East))
                else -> setOf(move(Direction.West))
            }
        }
        '\\' -> {
            when (direction) {
                Direction.East -> setOf(move(Direction.South))
                Direction.West -> setOf(move(Direction.North))
                Direction.North -> setOf(move(Direction.West))
                else -> setOf(move(Direction.East))
            }
        }
        '-' -> {
            if (direction.y == 0) {
                setOf(move(direction))
            } else {
                setOf(
                    move(Direction.West),
                    move(Direction.East)
                )
            }
        }
        '|' -> {
            if (direction.x == 0) {
                setOf(move(direction))
            } else {
                setOf(
                    move(Direction.North),
                    move(Direction.South),
                )
            }
        }
        else -> throw Error("Could not find next beam for $this $c")
    }
}

private fun Beam.move(direction: Direction): Beam {
    return Beam(pos.move(direction), direction)
}

private fun Pair<Int, Int>.move(direction: Direction): Pair<Int, Int> {
    return Pair(first + direction.x, second + direction.y)
}
