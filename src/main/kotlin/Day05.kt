typealias Stacks = MutableMap<Int, ArrayDeque<Char>>

fun main() = day(5) {

    fun solve(crane: (stacks: Stacks, Int, Int, Int) -> Unit): String {
        val (firstPart, secondPart) = inputString.split("\n\n").map { it.lines() }

        val stacks: Stacks = sortedMapOf()
        firstPart.take(firstPart.size - 1).forEach { l ->
            Regex("""(\[.]| {3})""").findAll(l.filterIndexed { index, _ -> ((index + 1) % 4) != 0 })
                .forEachIndexed { index, result ->
                    result.value.singleOrNull(Char::isLetter)
                        ?.let { stacks.getOrPut(index + 1) { ArrayDeque() }.addLast(it) }
                }
        }

        secondPart.forEach { l ->
            val (amount, from, to) = l.removePrefix("move ").split(" from ", " to ")
                .map { it.toInt() }
            crane(stacks, amount, from, to)
        }

        return stacks.map { it.value.first() }.joinToString("")
    }

    part1 {
        solve { stacks, amount, from, to ->
            repeat(amount) {
                stacks[to]!!.addFirst(stacks[from]!!.removeFirst())
            }
        }
    }

    part2 {
        solve { stacks, amount, from, to ->
            val moving = (1..amount).map { stacks[from]!!.removeFirst() }
            moving.reversed().forEach {
                stacks[to]!!.addFirst(it)
            }
        }
    }

    expectPart1 = "CMZ"
    expectPart2 = "MCD"
}
