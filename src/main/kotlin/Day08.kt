fun main() = day(8) {

    data class Pos(val x: Int, val y: Int)

    val grid = mutableMapOf<Pos, Int>()

    inputLines.forEachIndexed { x, l ->
        l.forEachIndexed { y, c -> grid[Pos(x, y)] = c.digitToInt() }
    }

    fun lineOfSight(pos: Pos, x: Int = pos.x, y: Int = pos.y) = listOf(
        (x - 1 downTo 0).map { Pos(it, y) },
        (y - 1 downTo 0).map { Pos(x, it) },
        (x + 1..inputLines.first().lastIndex).map { Pos(it, y) },
        (y + 1..inputLines.lastIndex).map { Pos(x, it) }
    )

    part1 {
        grid.entries.count { (point, treeHeight) ->
            lineOfSight(point)
                .any { l -> l.all { grid[it]!! < treeHeight } }
        }
    }

    part2 {
        grid.entries.maxOf { (point, treeHeight) ->
            lineOfSight(point).fold(1) { acc, l ->
                acc * l.indexOfFirst { grid[it]!! >= treeHeight }
                    .let { if (it == -1) l.size else it + 1 }
            }
        }
    }

    expectPart1 = 21
    expectPart2 = 8
}
