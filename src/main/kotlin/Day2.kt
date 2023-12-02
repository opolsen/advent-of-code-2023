class Day2 {
    private val limits = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun solve1(games: List<String>): Int {
        val validGames = mutableListOf<Int>()
        games.forEach {
            val id = Integer.parseInt(it.split(":")[0].substring(5))
            var isValid = true
            val gameBags = it.split(":")[1].split(";")
            gameBags.forEach { bag ->
                bag.split(",").forEach { bagResult ->
                    val number = Integer.parseInt(bagResult.trim().split(" ")[0])
                    val colour = bagResult.trim().split(" ")[1]
                    limits[colour]?.let { colourLimit ->
                        if (colourLimit < number) {
                            isValid = false
                            println("Over limit in game $id for color $colour: $number")
                        }
                    }
                }
            }
            if (isValid) {
                validGames.add(id)
            }
        }
        println("Valid games: $validGames")
        return validGames.sum()
    }

    fun solve2(games: List<String>): Int {
        val powers = mutableListOf<Int>()
        games.forEach {
            val maxCubesOfColor = mutableMapOf<String, Int>()
            val gameBags = it.split(":")[1].split(";")
            gameBags.forEach { bag ->
                bag.split(",").forEach { bagResult ->
                    val number = Integer.parseInt(bagResult.trim().split(" ")[0])
                    val colour = bagResult.trim().split(" ")[1]
                    maxCubesOfColor[colour] = maxOf(maxCubesOfColor[colour] ?: 0, number)
                }
            }
            println(maxCubesOfColor)
            powers.add(maxCubesOfColor.values.reduce { acc, i -> acc * i })
        }
        return powers.sum()
    }
}