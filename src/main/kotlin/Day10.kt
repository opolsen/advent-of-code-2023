class Day10 {
    private enum class Direction(val x: Int, val y: Int) {
        North(0, -1), West(-1, 0), South(0, 1), East(1, 0)
    }

    fun solve1(lines: List<String>): Int {
        val loop = findLoop(lines)
        return Math.floorDiv(loop.size, 2)
    }

    fun solve2(lines: List<String>): Int {
        val loop = findLoop(lines).toSet()
        val maxY = lines.size - 1
        val maxX = lines.first().count() - 1
        val todo = mutableSetOf<Pair<Int, Int>>()
        val checked = mutableSetOf<Pair<Int, Int>>()
        val unenclosed = mutableSetOf<Pair<Int, Int>>()
        unenclosed.addAll(getInitialUnenclosed(maxX, maxY, loop))
        todo.addAll(unenclosed)
        while (todo.isNotEmpty()) {
            val newTodos = mutableSetOf<Pair<Int, Int>>()
            todo.removeAll { it in checked }
            todo.forEach { p ->
                if (p !in loop) {
                    unenclosed.add(p)
                    newTodos.addAll(p.getAdjacent(maxX, maxY))
                }
                checked.add(p)
            }
            todo.addAll(newTodos)
        }
        return (maxX+1)*(maxY+1) - unenclosed.size - loop.size
    }

    private fun findLoop(lines: List<String>): List<Pair<Int, Int>> {
        val yStart = lines.indexOfFirst { it.contains('S') }
        val xStart = lines[yStart].indexOfFirst { it == 'S' }
        val start = Pair(xStart, yStart)
        val max = Pair(lines.size - 1, lines.first().count() - 1)
        val loop = mutableListOf(start)
        var direction: Direction? = null
        while (loop.count { it == start } < 2) {
            val currentNode = loop.last()
            if (currentNode == start) {
                if (currentNode.first < max.first && lines[currentNode.second][currentNode.first + 1] in setOf('-', 'J', '7')) {
                    loop.add(Pair(currentNode.first + 1, currentNode.second))
                    direction = nextDirection(lines, currentNode, Direction.East)
                }
                else if (currentNode.first > 0 && lines[currentNode.second][currentNode.first - 1] in setOf('-', 'L', 'F')) {
                    loop.add(Pair(currentNode.first - 1, currentNode.second))
                    direction = nextDirection(lines, currentNode, Direction.West)
                }
                else if (currentNode.second > 0 && lines[currentNode.second - 1][currentNode.first] in setOf('|', '7', 'F')) {
                    loop.add(Pair(currentNode.first, currentNode.second - 1))
                    direction = nextDirection(lines, currentNode, Direction.North)
                }
                else if (currentNode.second < max.second && lines[currentNode.second + 1][currentNode.first] in setOf('|', 'J', 'L')) {
                    loop.add(Pair(currentNode.first, currentNode.second + 1))
                    direction = nextDirection(lines, currentNode, Direction.South)
                } else {
                    throw Error("Found no connections to loop")
                }
                continue
            }
            loop.add(Pair(currentNode.first + direction!!.x, currentNode.second + direction.y))
            direction = nextDirection(lines, currentNode, direction)
        }
        return loop
    }

    private fun nextDirection(lines: List<String>, currentPosition: Pair<Int, Int>, currentDirection: Direction): Direction {
        val nextPosition = lines[currentPosition.second + currentDirection.y][currentPosition.first + currentDirection.x]
        if (nextPosition == 'S') {
            return currentDirection
        }
        if (currentDirection.x != 0) {
            return when (nextPosition) {
                '-' -> currentDirection
                'J' -> Direction.North
                'L' -> Direction.North
                'F' -> Direction.South
                '7' -> Direction.South
                else -> throw Error("Could not find next part of loop")
            }
        }
        if (currentDirection.y != 0) {
            return when (nextPosition) {
                '|' -> currentDirection
                '7' -> Direction.West
                'J' -> Direction.West
                'F' -> Direction.East
                'L' -> Direction.East
                else -> throw Error("Could not find next part of loop")
            }
        }
        throw Error("Could not find next part of loop")
    }

    private fun getInitialUnenclosed(maxX: Int, maxY: Int, loop: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
        val unenclosed = mutableSetOf<Pair<Int, Int>>()
        (0..maxX).forEach {
            if (Pair(it, 0) !in loop) {
                unenclosed.add(Pair(it, 0))
            }
            if (Pair(it, maxY) !in loop) {
                unenclosed.add(Pair(it, maxY))
            }
        }
        (0..maxY).forEach {
            if (Pair(0, it) !in loop) {
                unenclosed.add(Pair(0, it))
            }
            if (Pair(maxX, it) !in loop) {
                unenclosed.add(Pair(maxX, it))
            }
        }
        return unenclosed
    }

    private fun Pair<Int, Int>.getAdjacent(maxX: Int, maxY: Int): List<Pair<Int, Int>> {
        val adjacent = mutableListOf<Pair<Int, Int>>()
        if (first > 0) {
            adjacent.add(Pair(first - 1, second))
        }
        if (first < maxX) {
            adjacent.add(Pair(first + 1, second))
        }
        if (second > 0) {
            adjacent.add(Pair(first, second - 1))
        }
        if (second < maxY) {
            adjacent.add(Pair(first, second + 1))
        }
        return adjacent
    }
}
