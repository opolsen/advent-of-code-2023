class Day19 {
    fun solve1(lines: List<String>): Int {
        val workflows = lines
            .takeWhile { it.isNotBlank() }
            .map { it.toWorkflow() }
            .groupBy { it.name }
            .mapValues { it.value.first() }
        val parts = lines.subList(workflows.size + 1, lines.size).map { it.toPart() }
        return parts.filter { it.isAccepted(workflows) }.sumOf { it.x + it.m + it.a + it.s }
    }

    private fun String.toWorkflow(): Workflow {
        val name = Regex("\\S+(?=\\{)").find(this)?.value ?: throw Error("Could not find name")
        val conditions = Regex("(?<=\\{)\\S+(?=})").find(this)?.value?.split(",")?.map { it.toCondition() }
            ?: throw Error("Could not find conditions")
        return Workflow(name, conditions)
    }

    private fun String.toCondition(): Condition {
        if (this.contains(":")) {
            val split = this.split(":")
            val selector = split[0].first()
            val comparator = split[0].drop(1).first()
            val value = split[0].drop(2).toInt()
            return Condition(selector, comparator, value, split[1])
        } else {
            return Condition(null, null, null, this)
        }
    }

    private fun String.toPart(): Part {
        val x = Regex("(?<=x=)\\d+").find(this)?.value?.toInt() ?: throw Error("Could not find x")
        val m = Regex("(?<=m=)\\d+").find(this)?.value?.toInt() ?: throw Error("Could not find m")
        val a = Regex("(?<=a=)\\d+").find(this)?.value?.toInt() ?: throw Error("Could not find a")
        val s = Regex("(?<=s=)\\d+").find(this)?.value?.toInt() ?: throw Error("Could not find s")
        return Part(x, m, a, s)
    }

    private data class Workflow(val name: String, val conditions: List<Condition>)

    private data class Condition(
        val selector: Char?,
        val comparator: Char?,
        val comparisonValue: Int?,
        val destination: String
    )

    private data class Part(val x: Int, val m: Int, val a: Int, val s: Int) {
        fun isAccepted(workflows: Map<String, Workflow>): Boolean {
            var current = "in"
            while (current !in setOf("A", "R")) {
                val workflow = workflows[current]!!
                for (c in workflow.conditions) {
                    if (this.satisfies(c)) {
                        current = c.destination
                        break
                    }
                }
            }
            return current == "A"
        }

        private fun satisfies(condition: Condition): Boolean {
            condition.comparator?.let {
                val partValue = this.select(condition.selector)
                val comparisonValue = condition.comparisonValue!!
                if (condition.comparator == '>') {
                    return partValue > comparisonValue
                }
                if (condition.comparator == '<') {
                    return partValue < comparisonValue
                }
            }
            return true
        }

        private fun select(selector: Char?): Int {
            return when (selector) {
                'x' -> x
                'm' -> m
                'a' -> a
                's' -> s
                else -> throw Error("Could not select $selector")
            }
        }
    }
}
