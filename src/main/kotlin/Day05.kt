fun main() = day(5) {

    // ! DO NOT JUDGE
    // ! THIS IS AN INSANELY MESSY SOLUTION I CREATED
    // ! AT 6am
    // ! WILL IMPROVE LATER BUT NOW I'LL GO TO BED AGAIN

    part1 {
        val (firstPart, secondPart) = inputString.split("\n\n").map { it.lines() }

        val ids = firstPart.last().withIndex().filterNot { it.value.isWhitespace() }.map { it.value.digitToInt() to it.index }

        val stacks = mutableMapOf<Int, ArrayDeque<Char>>()

        ids.forEach {
            stacks[it.first] = ArrayDeque()
        }

        firstPart.take(firstPart.size - 1)
            .forEach { l ->
                ids.forEach {
                    l.getOrNull(it.second)?.let { c ->
                        if (!c.isWhitespace())
                            stacks[it.first]!!.add(c)
                    }
                }
            }

        secondPart.forEach {
            val (amount, from, to) = it.removePrefix("move ").split(" from ", " to ")
                .map { it.toInt() }

            repeat(amount) {
                stacks[to]!!.addFirst(stacks[from]!!.removeFirst())
            }
        }

        var string = ""
        repeat(stacks.size) {
            string += stacks[it + 1]!!.first()
        }

        string
    }

    part2 {
        val (firstPart, secondPart) = inputString.split("\n\n").map { it.lines() }

        val ids = firstPart.last().withIndex().filterNot { it.value.isWhitespace() }.map { it.value.digitToInt() to it.index }

        val stacks = mutableMapOf<Int, ArrayDeque<Char>>()

        ids.forEach {
            stacks[it.first] = ArrayDeque()
        }

        firstPart.take(firstPart.size - 1)
            .forEach { l ->
                ids.forEach {
                    l.getOrNull(it.second)?.let { c ->
                        if (!c.isWhitespace())
                            stacks[it.first]!!.add(c)
                    }
                }
            }

        secondPart.forEach {
            val (amount, from, to) = it.removePrefix("move ").split(" from ", " to ")
                .map { it.toInt() }

            val moving = (1..amount).map { stacks[from]!!.removeFirst() }
            moving.reversed().forEach {
                stacks[to]!!.addFirst(it)
            }
        }

        var string = ""
        repeat(stacks.size) {
            string += stacks[it + 1]!!.first()
        }

        string
    }

    expectPart1 = "CMZ"
    expectPart2 = "MCD"
}
