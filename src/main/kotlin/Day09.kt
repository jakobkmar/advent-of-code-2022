import kotlin.math.sign

fun main() = day(9) {

    data class Pos(val x: Int, val y: Int) {
        val adjacent get() = (-1..1).flatMap { dx -> (-1..1).map { dy -> Pos(x + dx, y + dy) } }
    }

    var moves = sequence {
        var x = 0; var y = 0
        yield(Pos(x, y))
        for (line in inputLines) {
            repeat(line.substringAfter(' ').toInt()) {
                when (line[0]) {
                    'L' -> x--; 'R' -> x++
                    'U' -> y--; 'D' -> y++
                }
                yield(Pos(x, y))
            }
        }
    }

    fun Sequence<Pos>.pull() = scan(Pos(0, 0)) { tail, head ->
        if (tail in head.adjacent) return@scan tail
        Pos(tail.x + (head.x - tail.x).sign, tail.y + (head.y - tail.y).sign)
    }

    part1 {
        moves.pull().toSet().size
    }

    part2 {
        repeat(9) { moves = moves.pull() }
        moves.toSet().size
    }

    expectPart1 = 13
    expectPart2 = 36
}
