import kotlin.math.min

class Day17 {
    fun solve1(lines: List<String>): Int {
        val positions = mutableSetOf<Pair<Int, Int>>()
        val distances = mutableMapOf<Pair<Pair<Int, Int>, Pair<Direction, Int>>, Int>().withDefault { Int.MAX_VALUE }
        lines.indices.forEach { y -> lines[0].indices.forEach { x -> positions.add(Pair(x, y)) } }
        val initialNode = Node(Pair(0, 0), 0, mutableListOf())
        val goal = Pair(lines.size - 1, lines[0].count() - 1)
        val todos = mutableSetOf(initialNode)
        while (todos.isNotEmpty()) {
            val current = todos.minBy { it.distance }
            if (current.pos == goal) {
                return current.distance
            }
            getValidNeighbours(current, 0, 3).filter { it.first in positions }.forEach { neighbour ->
                val tentativeDistance = current.distance + lines[neighbour.first.second][neighbour.first.first].digitToInt()
                if (tentativeDistance < distances.getValue(neighbour)) {
                    distances[neighbour] = tentativeDistance
                    todos.add(
                        Node(
                            neighbour.first,
                            tentativeDistance,
                            current.path.plus(neighbour.second.first)
                        )
                    )
                }
            }
            todos.remove(current)
        }
        throw Error("No solution found")
    }

    fun solve2(lines: List<String>): Int {
        val positions = mutableSetOf<Pair<Int, Int>>()
        val distances = mutableMapOf<Pair<Pair<Int, Int>, Pair<Direction, Int>>, Int>().withDefault { Int.MAX_VALUE }
        lines.indices.forEach { y -> lines[0].indices.forEach { x -> positions.add(Pair(x, y)) } }
        val initialNode = Node(Pair(0, 0), 0, mutableListOf())
        val goal = Pair(lines[0].count() - 1, lines.size - 1)
        val todos = mutableSetOf(initialNode)
        while (todos.isNotEmpty()) {
            val current = todos.minBy { it.distance }
            if (current.pos == goal) {
                val currentDir = current.path.last()
                if (current.path.reversed().take(4).count { it == currentDir } == 4) {
                    return current.distance
                }
            }
            getValidNeighbours(current, 4, 10).filter { it.first in positions }.forEach { neighbour ->
                val tentativeDistance = current.distance + lines[neighbour.first.second][neighbour.first.first].digitToInt()
                if (tentativeDistance < distances.getValue(neighbour)) {
                    distances[neighbour] = tentativeDistance
                    todos.add(
                        Node(
                            neighbour.first,
                            tentativeDistance,
                            current.path.plus(neighbour.second.first)
                        )
                    )
                }
            }
            todos.remove(current)
        }
        throw Error("No solution found")
    }


    private fun getValidNeighbours(current: Node, min: Int, max: Int): List<Pair<Pair<Int, Int>, Pair<Direction, Int>>> {
        val currentDir = current.path.lastOrNull()
        val validDirections = Direction.entries
            .filter { d -> currentDir == null || currentDir == d || current.path.reversed().take(min).count { it == currentDir } == min }
            .filter { d -> current.path.reversed().takeWhile { it == d }.size < max }
            .filter { d -> !setOf(d, currentDir).containsAll(setOf(Direction.East, Direction.West)) }
            .filter { d -> !setOf(d, currentDir).containsAll(setOf(Direction.North, Direction.South)) }
        return validDirections.map { d -> Pair(Pair(current.pos.first + d.x, current.pos.second + d.y), Pair(d, current.path.reversed().takeWhile { it == d }.size)) }
    }

    private data class Node(
        val pos: Pair<Int, Int>,
        var distance: Int,
        val path: List<Direction>
    )

    private enum class Direction(val x: Int, val y: Int) {
        North(0, -1), East(1, 0), South(0, 1), West(-1, 0)
    }
}
