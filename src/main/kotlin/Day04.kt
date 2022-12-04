fun main() = day(4) {
    part1 {
        inputLines
            .map {
                val ranges = it.split(',').map {
                    val ints = it.split('-')
                    ints[0].toInt()..ints[1].toInt()
                }
                ranges[0] to ranges[1]
            }
            .count { pair -> pair.first.all { it in pair.second } || pair.second.all { it in pair.first } }
    }

    part2 {
        inputLines
            .map {
                val ranges = it.split(',').map {
                    val ints = it.split('-')
                    ints[0].toInt()..ints[1].toInt()
                }
                ranges[0] to ranges[1]
            }
            .count { pair -> pair.first.any { it in pair.second } || pair.second.any { it in pair.first } }
    }

    expectPart1 = 2
    expectPart2 = 4
}
