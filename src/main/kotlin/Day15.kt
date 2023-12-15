class Day15 {
    fun solve1(input: String): Int {
        val parts = input.split(",")
        return parts.sumOf { hash(it) }
    }

    fun solve2(input: String): Int {
        val parts = input.split(",")
        val boxes = getBoxes(parts)
        return boxes.keys.sumOf { boxNumber ->
            boxes.getOrDefault(boxNumber, emptyList())
                .mapIndexed { lensSlot, lens -> focusingPower(boxNumber, lensSlot, lens.second) }
                .sum()
        }
    }

    private fun getBoxes(parts: List<String>): Map<Int, List<Pair<String, Int>>> {
        val boxes = mutableMapOf<Int, List<Pair<String, Int>>>()
        parts.forEach { part ->
            val label = part.takeWhile { it != '=' && it != '-' }
            val boxNumber = hash(label)
            val lensesInBox = boxes.getOrDefault(boxNumber, mutableListOf()).toMutableList()
            val operation = part[label.length]

            if (operation == '-') {
                boxes[boxNumber] = lensesInBox.filter { it.first != label }
            } else if (operation == '=') {
                val indexOfLabel = lensesInBox.indexOfFirst { it.first == label }
                val length = part.substring(label.length + 1).toInt()
                val newLens = Pair(label, length)
                if (indexOfLabel > -1) {
                    lensesInBox[indexOfLabel] = newLens
                } else {
                    lensesInBox.add(newLens)
                }
                boxes[boxNumber] = lensesInBox
            }
        }
        return boxes
    }

    private fun focusingPower(boxNumber: Int, lensSlot: Int, focalLength: Int): Int {
        return (1 + boxNumber) * (1 + lensSlot) * focalLength
    }

    private fun hash(part: String): Int {
        var current = 0
        part.forEach { c ->
            current += c.code
            current *= 17
            current = current.mod(256)
        }
        return current
    }
}