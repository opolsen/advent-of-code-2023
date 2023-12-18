class Day18 {
    fun solve1(lines: List<String>): Int {
        val edges = getEdges(lines)
        val corners = getCorners(edges)
        val unenclosed = getUnenclosed(edges)
        return ((corners.maxX - corners.minX + 1) * (corners.maxY - corners.minY + 1)) - unenclosed.size
    }

    fun solve2(lines: List<String>): Long {
        return 0
    }

    private fun getEdges(lines: List<String>): Set<Edge> {
        val edges = mutableSetOf<Edge>()
        var currentPos = Pair(0, 0)
        lines.forEach { line ->
            val split = line.split(" ")
            val direction = split[0].first().toDirection()
            val length = split[1].toInt()
            for (i in 0..<length) {
                currentPos = Pair(currentPos.first + direction.x, currentPos.second + direction.y)
                edges.add(Edge(currentPos))
            }
        }
        return edges
    }

    private fun getCorners(edges: Set<Edge>): Corners {
        val minX = edges.minOf { it.pos.first }
        val minY = edges.minOf { it.pos.second }
        val maxY = edges.maxOf { it.pos.second }
        val maxX = edges.maxOf { it.pos.first }
        return Corners(minX, minY, maxX, maxY)
    }

    private fun getUnenclosed(edges: Set<Edge>): MutableSet<Pair<Int, Int>> {
        val edgePositions = edges.map { it.pos }.toSet()
        val todo = mutableSetOf<Pair<Int, Int>>()
        val checked = mutableSetOf<Pair<Int, Int>>()
        val unenclosed = mutableSetOf<Pair<Int, Int>>()
        unenclosed.addAll(getInitialUnenclosed(edges))
        todo.addAll(unenclosed)
        while (todo.isNotEmpty()) {
            val newTodos = mutableSetOf<Pair<Int, Int>>()
            todo.removeAll { it in checked }
            todo.forEach { pos ->
                if (pos !in edgePositions) {
                    unenclosed.add(pos)
                    newTodos.addAll(pos.getAdjacent(getCorners(edges)))
                }
                checked.add(pos)
            }
            todo.addAll(newTodos)
        }
        return unenclosed
    }

    private fun getInitialUnenclosed(edges: Set<Edge>): Set<Pair<Int, Int>> {
        val corners = getCorners(edges)
        val edgePositions = edges.map { it.pos }.toSet()
        val unenclosed = mutableSetOf<Pair<Int, Int>>()
        (corners.minX..corners.maxX).forEach {
            if (Pair(it, corners.minY) !in edgePositions) {
                unenclosed.add(Pair(it, corners.minY))
            }
            if (Pair(it, corners.maxY) !in edgePositions) {
                unenclosed.add(Pair(it, corners.maxY))
            }
        }
        (corners.minY..corners.maxY).forEach {
            if (Pair(corners.minX, it) !in edgePositions) {
                unenclosed.add(Pair(corners.minX, it))
            }
            if (Pair(corners.maxX, it) !in edgePositions) {
                unenclosed.add(Pair(corners.maxX, it))
            }
        }
        return unenclosed
    }

    private fun Pair<Int, Int>.getAdjacent(corners: Corners): List<Pair<Int, Int>> {
        val adjacent = mutableListOf<Pair<Int, Int>>()
        if (first > corners.minX) {
            adjacent.add(Pair(first - 1, second))
        }
        if (first < corners.maxX) {
            adjacent.add(Pair(first + 1, second))
        }
        if (second > corners.minY) {
            adjacent.add(Pair(first, second - 1))
        }
        if (second < corners.maxY) {
            adjacent.add(Pair(first, second + 1))
        }
        return adjacent
    }

    private data class Edge(val pos: Pair<Int, Int>)

    private enum class Direction(val x: Int, val y: Int) {
        North(0, -1), West(-1, 0), South(0, 1), East(1, 0)
    }

    private fun Char.toDirection(): Direction {
        return when (this) {
            'U', '3' -> Direction.North
            'L', '2' -> Direction.West
            'R', '0' -> Direction.East
            'D', '1' -> Direction.South
            else -> throw Error("Unknown char: $this")
        }
    }

    private data class Corners(val minX: Int, val minY: Int, val maxX: Int, val maxY: Int)
}