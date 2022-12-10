fun main() = day(10) {

    val xValues = inputLines.flatMap {
        if (it == "noop") listOf(0) else listOf(0, it.split(' ')[1].toInt())
    }.scan(1, Int::plus)

    part1 {
        (20..220 step 40).sumOf { it * xValues[it - 1] }
    }

    part2 {
        val screen = xValues
            .chunked(40)
            .joinToString("\n") {
                it.withIndex().joinToString("") { (index, xValue) ->
                    if (xValue in (index - 1)..(index + 1)) "██" else "  "
                }
            }
        println(screen)
    }

    expectPart1 = 13140
    expectPart2 = Unit
}
