typealias Network = Map<String, Pair<String, String>>

class Day8 {
    fun solve1(lines: List<String>): Int {
        val instructions = lines[0].toCharArray()
        val network = toNetwork(lines.drop(2))
        var location = "AAA"
        var index = 0
        var steps = 0
        while (location != "ZZZ") {
            location = nextLocation(instructions[index], location, network)
            steps++
            index++
            if (index >= instructions.size) {
                index = 0
            }
        }
        return steps
    }

    fun solve2Naive(lines: List<String>): Int {
        val instructions = lines[0].toCharArray()
        val network = toNetwork(lines.drop(2))
        var locations = network.keys.filter { it.endsWith("A") }
        var index = 0
        var steps = 0
        while (!locations.all { it.endsWith("Z") }) {
            locations = locations.map { nextLocation(instructions[index], it, network) }
            steps++
            index++
            if (index >= instructions.size) {
                index = 0
            }
        }
        return steps
    }

    fun solve2(lines: List<String>): Long {
        val instructions = lines[0].toCharArray()
        val network = toNetwork(lines.drop(2))
        var index = 0
        val locations = network.keys.filter { it.endsWith("A") }
        val lengths = locations.map {
            var location = it
            var steps = 0L
            while (!location.endsWith("Z")) {
                location = nextLocation(instructions[index], location, network)
                steps++
                index++
                if (index >= instructions.size) {
                    index = 0
                }
            }
            steps
        }
        return lcm(lengths)
    }

    private fun toNetwork(lines: List<String>): Network {
        val network = mutableMapOf<String, Pair<String, String>>()
        lines.forEach {
            val split = it.split(" = ")
            val key = split[0]
            val left = split[1].split(", ")[0].filter { c -> c.isLetterOrDigit() }
            val right = split[1].split(", ")[1].filter { c -> c.isLetterOrDigit() }
            network[key] = Pair(left, right)
        }
        return network
    }

    private fun nextLocation(instruction: Char, location: String, network: Network): String {
        return when (instruction) {
            'L' -> network[location]?.first
            'R' -> network[location]?.second
            else -> throw Error("Unknown instruction")
        } ?: throw Error("Did not find location")
    }

    private fun lcm(values: List<Long>): Long {
        return values.reduce { acc, v -> lcm(acc, v) }
    }

    // Calc lcm using gcd: https://en.wikipedia.org/wiki/Least_common_multiple#Using_the_greatest_common_divisor
    private fun lcm(a: Long, b: Long): Long {
        return a * b / gcd(a, b)
    }

    // Calc gcd using Euclidian algorithm: https://en.wikipedia.org/wiki/Euclidean_algorithm#Implementations
    private fun gcd(a: Long, b: Long): Long {
        var varA = a
        var varB = b
        while (varB != 0L) {
            val t = varB
            varB = varA.mod(varB)
            varA = t
        }
        return varA
    }
}