class Day17 {
    fun solve1(lines: List<String>): Int {
        val nodes = mutableSetOf<Node>()
        val unvisited = mutableSetOf<Pair<Int, Int>>()
        lines.indices.forEach { y ->
            lines[0].indices.forEach { x ->
                val distance = if (y == 0 && x == 0) {
                    0
                } else {
                    Int.MAX_VALUE
                }
                nodes.add(Node(Pair(x, y), distance, false, mutableListOf()))
                unvisited.add(Pair(x, y))
            }
        }
        val initialNode = nodes.first { it.pos == Pair(0, 0) }
        val goal = Pair(lines.size - 1, lines[0].count() - 1)
        var current = initialNode
        while (true) {
            val unvisitedNeighbours = getUnvisitedNeighbours(current, unvisited)
            unvisitedNeighbours.forEach { unvisitedNeighbour ->
                val unvisitedNeighbourNode = nodes.firstOrNull { it.pos == Pair(unvisitedNeighbour.first, unvisitedNeighbour.second) }
                unvisitedNeighbourNode?.let {
                    val tentativeDistance = current.distance + lines[unvisitedNeighbour.second][unvisitedNeighbour.first].digitToInt()
                    if (tentativeDistance < unvisitedNeighbourNode.distance) {
                        unvisitedNeighbourNode.distance = tentativeDistance
                    }
                }
            }

            if (current.pos == goal) {
                break
            }
            current.visited = true
            unvisited.remove(current.pos)
            current = nodes.filter { !it.visited }.minBy { it.distance }
        }
        println(current.path)
        return current.distance
    }

    private fun getUnvisitedNeighbours(current: Node, unvisited: MutableSet<Pair<Int, Int>>): List<Pair<Int, Int>> {
        return unvisited.filter { u ->
            Direction.entries.any { d -> u.first == current.pos.first + d.x && u.second == current.pos.second + d.y }
        }
    }

    private data class Node(
        val pos: Pair<Int, Int>,
        var distance: Int,
        var visited: Boolean,
        val path: MutableList<Direction>
    )

    private enum class Direction(val x: Int, val y: Int) {
        North(0, -1), East(1, 0), South(0, 1), West(-1, 0)
    }
}
