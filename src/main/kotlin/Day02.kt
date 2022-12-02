fun main() = day(2) {

    val options = "ABC".toList()

    fun Char.shift(n: Int) = options[(options.indexOf(this) + n) % 3]

    fun Char.xyzToAbc() = when (this) { 'X' -> 'A'; 'Y' -> 'B'; 'Z' -> 'C'; else -> error(Unit) }

    fun score(other: Char, me: Char) =
        when (me) { other -> 3; other.shift(1) -> 6; else -> 0 } +
            when (me) { 'A' -> 1; 'B' -> 2; 'C' -> 3; else -> error(Unit) }

    val input = inputLines.map { l -> l.split(' ').map { it[0] } }

    part1 {
        input.sumOf { (other, me) -> score(other, me.xyzToAbc()) }
    }

    part2 {
        input.sumOf { (other, iMust) ->
            val shift = when (iMust) { 'X' -> 2; 'Y' -> 0; 'Z' -> 1; else -> error(Unit) }
            score(other, other.shift(shift))
        }
    }

    expectPart1 = 15
    expectPart2 = 12
}
