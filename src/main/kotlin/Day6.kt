class Day6 {
    fun solve1(lines: List<String>): Int {
        val raceDurations = Regex("\\d+").findAll(lines[0]).map { it.value.toLong() }.toList()
        val recordDistances = Regex("\\d+").findAll(lines[1]).map { it.value.toLong() }.toList()
        return raceDurations.indices
            .map { i -> getRecordDurations(raceDurations[i], recordDistances[i]).size }
            .reduce { acc, i -> acc * i }
    }

    fun solve2(lines: List<String>): Int {
        val raceDuration = Regex("\\d+").findAll(lines[0]).map { it.value.toLong() }.joinToString("").toLong()
        val recordDistance = Regex("\\d+").findAll(lines[1]).map { it.value.toLong() }.joinToString("").toLong()
        return getRecordDurations(raceDuration, recordDistance).size
    }

    private fun getRecordDurations(raceDuration: Long, recordDistance: Long) =
        LongRange(0, raceDuration).filter { waitDuration ->
            val distance = (raceDuration - waitDuration) * waitDuration
            distance > recordDistance
        }
}