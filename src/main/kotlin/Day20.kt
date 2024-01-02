class Day20 {
    fun solve1(lines: List<String>, buttonPresses: Int): Int {
        val modules = lines.map { it.toModule() }.groupBy { it.name }.mapValues { it.value.first() }

        modules.forEach { (key, value) ->
            value.targets.forEach { target ->
                val targetModule = modules[target]
                if (targetModule != null && targetModule is Conjunction) {
                    targetModule.addInput(key)
                }
            }
        }

        var lowPulses = 0
        var highPulses = 0
        var currentButtonPresses = 0
        val pulses = ArrayDeque<Pulse>()
        val initialPulse = Pulse("button", PulseType.Low, "broadcaster")

        while (currentButtonPresses < buttonPresses) {
            pulses.add(initialPulse)
            while (pulses.isNotEmpty()) {
                val pulse = pulses.removeFirst()
                if (pulse.type == PulseType.Low) {
                    lowPulses++
                } else {
                    highPulses++
                }
                modules[pulse.target]?.let {
                    pulses.addAll(it.handlePulse(pulse))
                }
            }
            currentButtonPresses++
        }

        return lowPulses * highPulses
    }

    private fun String.toModule(): Module {
        val split = this.split("->")
        val targets = split[1].split(",").map { it.trim() }
        return if (split[0].trim() == "broadcaster") {
            Broadcaster("broadcaster", targets)
        } else {
            val name = split[0].drop(1).trim()
            if (split[0].first() == '%') {
                FlipFlop(name, targets)
            } else {
                Conjunction(name, targets)
            }
        }
    }

    private abstract class Module(open val name: String, open val targets: List<String>) {
        abstract fun handlePulse(pulse: Pulse): List<Pulse>
    }

    private data class Broadcaster(override val name: String, override val targets: List<String>) :
        Module(name, targets) {
        override fun handlePulse(pulse: Pulse): List<Pulse> {
            return targets.map { Pulse(name, pulse.type, it) }
        }
    }

    private data class FlipFlop(
        override val name: String,
        override val targets: List<String>,
        var on: Boolean = false
    ) : Module(name, targets) {
        override fun handlePulse(pulse: Pulse): List<Pulse> {
            if (pulse.type == PulseType.Low) {
                if (on) {
                    on = false
                    return targets.map { Pulse(name, PulseType.Low, it) }
                } else {
                    on = true
                    return targets.map { Pulse(name, PulseType.High, it) }
                }
            }
            return emptyList()
        }
    }

    private data class Conjunction(
        override val name: String,
        override val targets: List<String>,
        private val previousInputs: MutableMap<String, PulseType> = mutableMapOf()
    ) : Module(name, targets) {
        fun addInput(name: String) {
            previousInputs[name] = PulseType.Low
        }

        override fun handlePulse(pulse: Pulse): List<Pulse> {
            previousInputs[pulse.source] = pulse.type
            return if (previousInputs.values.all { it == PulseType.High }) {
                targets.map { Pulse(name, PulseType.Low, it) }
            } else {
                targets.map { Pulse(name, PulseType.High, it) }
            }
        }
    }

    private data class Pulse(val source: String, val type: PulseType, val target: String)

    private enum class PulseType { High, Low }
}