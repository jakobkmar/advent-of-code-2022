fun main() = day(6) {

    fun findPacket(size: Int) = inputString.windowed(size)
        .indexOfFirst { it.toSet().size == size } + size

    part1 { findPacket(4) }
    part2 { findPacket(14) }

    expectPart1 = 7
    expectPart2 = 19
}
