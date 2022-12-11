fun main() = day(11) {

    val monkeys = mutableListOf<Monkey>()

    (inputLines + "").windowed(7, 7).forEach { lb ->
        val (opType, opAmount) = lb[2].trim().removePrefix("Operation: new = old ").split(' ')
        monkeys += Monkey(
            lb[1].trim().removePrefix("Starting items: ")
                .split(", ").mapTo(ArrayDeque()) { it.toLong() },
            operation = {
                val amount = if (opAmount == "old") it else opAmount.toLong()
                when (opType) {
                    "+" -> it + amount; "*" -> it * amount
                    else -> error(Unit)
                }
            },
            lb[3].takeLastWhile { it.isDigit() }.toInt(),
            lb[4].last().digitToInt(), lb[5].last().digitToInt()
        )
    }

    fun calcLevel(rounds: Int, postOp: (Long) -> Long): Long {
        repeat(rounds) { _ ->
            monkeys.forEach { m ->
                while (true) {
                    val (toIndex, toValue) = m.getNextThrow(postOp) ?: break
                    monkeys[toIndex].currentItems.addLast(toValue)
                }
            }
        }

        return monkeys.map { it.inspectCount }.sortedDescending()
            .let { it[0] * it[1] }
    }

    part1 {
        calcLevel(20) { it / 3 }
    }

    part2 {
        val upperBound = monkeys.fold(1L) { acc, monkey -> acc * monkey.test }
        calcLevel(10000) { it % upperBound }
    }

    expectPart1 = 10605L
    expectPart2 = 2713310158L
}

class Monkey(
    val currentItems: ArrayDeque<Long>,
    val operation: (Long) -> Long,
    val test: Int,
    private val testTrueIndex: Int,
    private val testFalseIndex: Int,
) {
    var inspectCount = 0L

    fun getNextThrow(postOperation: (Long) -> Long): Pair<Int, Long>? {
        val level = postOperation(operation(currentItems.removeFirstOrNull() ?: return null))
        inspectCount++
        return (if (level % test == 0L) testTrueIndex else testFalseIndex) to level
    }
}
