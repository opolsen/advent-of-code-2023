class Day14 {
    fun solve1(lines: List<String>): Int {
        return getNorthLoad(tilt(toGrid(lines), Direction.North))
    }

    fun solve2(lines: List<String>, n: Int): Int {
        return getNorthLoad(rotate(toGrid(lines), n))
    }

    private fun getNorthLoad(grid: Array<Array<Char>>): Int {
        var load = 0
        grid.indices.forEach { y ->
            grid[y].indices.forEach { x ->
                if (grid[y][x] == 'O') {
                    load += grid.size - y
                }
            }
        }
        return load
    }

    private fun toGrid(lines: List<String>): Array<Array<Char>> {
        return Array(lines.size) { y ->
            Array(lines[0].count()) { x ->
                lines[y][x]
            }
        }
    }

    private fun rotate(grid: Array<Array<Char>>, n: Int): Array<Array<Char>> {
        val seenAt = mutableMapOf<String, Int>()
        var iteration = 0
        var tiltedGrid = grid
        var foundLoop = false
        while (iteration < n) {
            tiltedGrid = tilt(tiltedGrid, Direction.North)
            tiltedGrid = tilt(tiltedGrid, Direction.West)
            tiltedGrid = tilt(tiltedGrid, Direction.South)
            tiltedGrid = tilt(tiltedGrid, Direction.East)
            val serializedGrid = tiltedGrid.joinToString("") { row -> row.joinToString("") }
            if (!foundLoop && serializedGrid in seenAt.keys) {
                foundLoop = true
                val previous = seenAt[serializedGrid]
                val loopLength = iteration - previous!!
                val remaining = n - iteration
                println("Found loop from $previous to $iteration with length $loopLength")
                iteration += remaining - remaining.mod(loopLength)
            }
            seenAt[serializedGrid] = iteration
            iteration++
        }
        return tiltedGrid
    }

    private fun tilt(grid: Array<Array<Char>>, direction: Direction): Array<Array<Char>> {
        val rockPositions = getRockPositionsForDirection(grid, direction)

        val newGrid = grid.copyOf()
        newGrid.indices.forEach { y ->
            newGrid.indices.forEach { x ->
                if (newGrid[y][x] == 'O') {
                    newGrid[y][x] = '.'
                }
            }
        }

        if (direction == Direction.North) {
            rockPositions.forEach { p ->
                val y = p.first.second + 1
                val count = p.second
                for (i in y..<y + count) {
                    newGrid[i][p.first.first] = 'O'
                }
            }
        }

        if (direction == Direction.West) {
            rockPositions.forEach { p ->
                val x = p.first.first + 1
                val count = p.second
                for (i in x..<x + count) {
                    newGrid[p.first.second][i] = 'O'
                }
            }
        }

        if (direction == Direction.South) {
            rockPositions.forEach { p ->
                val y = p.first.second - 1
                val count = p.second
                for (i in y - count + 1..y) {
                    newGrid[i][p.first.first] = 'O'
                }
            }
        }

        if (direction == Direction.East) {
            rockPositions.forEach { p ->
                val x = p.first.first - 1
                val count = p.second
                for (i in x - count + 1..x) {
                    newGrid[p.first.second][i] = 'O'
                }
            }
        }

        return newGrid
    }

    private fun getRockPositionsForDirection(grid: Array<Array<Char>>, direction: Direction): List<Pair<Pair<Int, Int>, Int>> {
        val rockPositions = mutableListOf<Pair<Pair<Int, Int>,Int>>()
        if (direction == Direction.North) {
            grid[0].indices.forEach { x ->
                val column = grid.map { it[x] }.joinToString("")
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
        }

        if (direction == Direction.West) {
            grid.indices.forEach { y ->
                val row = grid[y].joinToString("")
                val split = row.split("#")
                var rockPos = -1
                split.forEach { s ->
                    val rocks = s.filter { c -> c == 'O' }.length
                    if (rocks > 0) {
                        rockPositions.add(Pair(Pair(rockPos, y), rocks))
                    }
                    rockPos += s.length + 1
                }
            }
        }

        if (direction == Direction.South) {
            grid[0].indices.forEach { x ->
                val column = grid.map { it[x] }.joinToString("").reversed()
                val split = column.split("#")
                var rockPos = 0
                split.forEach { s ->
                    val rocks = s.filter { c -> c == 'O' }.length
                    if (rocks > 0) {
                        rockPositions.add(Pair(Pair(x, column.length - rockPos), rocks))
                    }
                    rockPos += s.length + 1
                }
            }
        }

        if (direction == Direction.East) {
            grid.indices.forEach { y ->
                val row = grid[y].joinToString("").reversed()
                val split = row.split("#")
                var rockPos = 0
                split.forEach { s ->
                    val rocks = s.filter { c -> c == 'O' }.length
                    if (rocks > 0) {
                        rockPositions.add(Pair(Pair(row.length - rockPos, y), rocks))
                    }
                    rockPos += s.length + 1
                }
            }
        }

        return rockPositions
    }

    private enum class Direction {
        North, West, South, East
    }
}