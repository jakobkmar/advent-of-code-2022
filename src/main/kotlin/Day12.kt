fun main() = day(12) {

    val heightmap = inputLines.flatMapIndexed { lIndex, l ->
        l.mapIndexed { cIndex, c -> Pos(cIndex, lIndex) to c }
    }.toMap(LinkedHashMap())

    val startPos = heightmap.entries.first { it.value == 'S' }.key
    val endPos = heightmap.entries.first { it.value == 'E' }.key
    heightmap[startPos] = 'a'; heightmap[endPos] = 'z'

    val distanceMap = buildMap dMap@{
        var curDistance = 0
        var checkNext = setOf(endPos)
        while (checkNext.isNotEmpty()) {
            checkNext = buildSet {
                for (check in checkNext) {
                    if (check in this@dMap) continue else put(check, curDistance)
                    val height = heightmap[check]!!
                    for (neighbour in check.neighbours)
                        if (height - (heightmap[neighbour] ?: Char.MIN_VALUE) <= 1)
                            add(neighbour)
                }
            }
            curDistance++
        }
    }

    part1 {
        distanceMap[startPos]
    }

    part2 {
        heightmap.filter { it.value == 'a' && it.key in distanceMap }
            .keys.minOf { distanceMap[it]!! }
    }

    expectPart1 = 31
    expectPart2 = 29
}
