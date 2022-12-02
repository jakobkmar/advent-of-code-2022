fun main() = day(2) {

    val abc = "ABC".toList()

    fun Char.shift(n: Int) = abc[(abc.indexOf(this) + n) % 3]

    fun score(other: Char, me: Char) =
        when (me) { other -> 3; other.shift(1) -> 6; else -> 0 } +
            abc.indexOf(me) + 1

    val input = inputLines.map { it.split(' ').map(String::first) }

    part1 {
        input.sumOf { (other, me) -> score(other, abc["XYZ".indexOf(me)]) }
    }

    part2 {
        input.sumOf { (other, iMust) ->
            val shift = when (iMust) { 'X' -> 2; 'Z' -> 1; else -> 0 }
            score(other, other.shift(shift))
        }
    }

    expectPart1 = 15
    expectPart2 = 12
}
