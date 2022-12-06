fun main() = day(6) {

    fun findPacket(size: Int) =
        inputString.toList().windowed(size).withIndex()
            .first { it.value.distinct().size == it.value.size }
            .index + size

    part1 {
        findPacket(4)
    }

    part2 {
        findPacket(14)
    }

    expectPart1 = 7
    expectPart2 = 19
}
