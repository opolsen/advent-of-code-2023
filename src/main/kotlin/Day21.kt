class Day21 {
    fun solve1(lines: List<String>, steps: Int): Int {
        val startY = lines.indexOfFirst { it.contains("S") }
        val startX = lines[startY].indexOfFirst { it == 'S' }
        val start = Pair(startX, startY)
        var currentSteps = 0
        var possibleNodes = setOf(start)
        while (currentSteps < steps) {
            val newNeighbours = mutableSetOf<Pair<Int, Int>>()
            possibleNodes.forEach { n ->
                getNeighbours(n)
                    .filter { it.first in lines[0].indices && it.second in lines.indices}
                    .filter { lines[it.second][it.first] != '#' }
                    .forEach { neighbour ->
                        newNeighbours.add(neighbour)
                    }
            }
            possibleNodes = newNeighbours
            currentSteps++
        }
        return possibleNodes.size
    }

    fun solve2(lines: List<String>, steps: Int): Int {
        val startY = lines.indexOfFirst { it.contains("S") }
        val startX = lines[startY].indexOfFirst { it == 'S' }
        val start = Pair(startX, startY)

        var currentSteps = 0
        var possibleNodes = setOf(start)
        var currentGrids = mutableMapOf(
            start to setOf(Pair(0, 0))
        ).withDefault { emptySet() }
        while (currentSteps < steps) {
            val newNeighbours = mutableSetOf<Pair<Int, Int>>()
            val newGrids = mutableMapOf<Pair<Int, Int>, Set<Pair<Int, Int>>>().withDefault { emptySet() }
            possibleNodes.forEach { node ->
                getNeighbours(node)
                    .forEach { neighbour ->
                        if (neighbour.first in lines[0].indices && neighbour.second in lines.indices) {
                            if (lines[neighbour.second][neighbour.first] != '#') {
                                newNeighbours.add(neighbour)
                                newGrids[neighbour] = newGrids.getValue(neighbour).union(currentGrids.getValue(node))
                            }
                        } else {
                            var x = neighbour.first
                            var y = neighbour.second
                            var xGridDiff = 0
                            var yGridDiff = 0
                            if (neighbour.first < 0) {
                                x = lines[0].count() - 1
                                xGridDiff = -1
                            }
                            else if (neighbour.first == lines[0].count()) {
                                x = 0
                                xGridDiff = 1
                            }
                            else if (neighbour.second < 0) {
                                y = lines.size - 1
                                yGridDiff = -1
                            }
                            else if (neighbour.second == lines.size) {
                                y = 0
                                yGridDiff = 1
                            } else {
                                throw Error("Could not find new neighbour")
                            }
                            val newNeighbour = Pair(x, y)
                            if (lines[newNeighbour.second][newNeighbour.first] != '#') {
                                newNeighbours.add(newNeighbour)
                                newGrids[newNeighbour] = newGrids
                                    .getValue(newNeighbour)
                                    .union(currentGrids.getValue(node).map { grid ->
                                        Pair(grid.first + xGridDiff, grid.second + yGridDiff)
                                    })
                            }
                        }
                    }
            }
            possibleNodes = newNeighbours
            currentGrids = newGrids
            currentSteps++
        }

        return currentGrids.values.sumOf { it.size }
    }

    private fun getNeighbours(node: Pair<Int, Int>): Set<Pair<Int, Int>> {
        return setOf(
            Pair(node.first - 1, node.second),
            Pair(node.first + 1, node.second),
            Pair(node.first, node.second - 1),
            Pair(node.first, node.second + 1),
        )
    }
}