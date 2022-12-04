fun main() = day(4) {

    val ranges = inputLines.map { l ->
        l.split(',').map {
            it.split('-').map(String::toInt)
                .let { (a, b) -> (a..b).toSet() }
        }
    }

    part1 {
        ranges.count { (r1, r2) -> r1.containsAll(r2) || r2.containsAll(r1) }
    }

    part2 {
        ranges.count { (r1, r2) -> (r1 intersect r2).isNotEmpty() }
    }

    expectPart1 = 2
    expectPart2 = 4
}
