fun main() = day(3) {

    val priorities = (('a'..'z') + ('A'..'Z')).mapIndexed { i, c -> c to i + 1 }.toMap()

    part1 {
        inputLines.map(String::toList)
            .sumOf { priorities[(it.take(it.size / 2) intersect it.takeLast(it.size / 2)).single()]!! }
    }

    part2 {
        inputLines.map(String::toSet).chunked(3)
            .sumOf { priorities[(it[0] intersect it[1] intersect it[2]).single()]!! }
    }

    expectPart1 = 157
    expectPart2 = 70
}
