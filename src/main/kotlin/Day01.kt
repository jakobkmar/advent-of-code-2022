fun main() = day(1) {
    part1 {
        inputString.split("\n\n")
            .maxOfOrNull { g -> g.lines().sumOf { it.toInt() } }
    }

    part2 {
        inputString.split("\n\n")
            .map { g -> g.lines().sumOf { it.toInt() } }
            .sortedDescending().take(3).sum()
    }

    expectPart1 = 24000
    expectPart2 = 45000
}
