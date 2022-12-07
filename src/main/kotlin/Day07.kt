fun main() = day(7) {

    val dirs = mutableSetOf(Dir("/", null))
    var currentDir = dirs.first()

    inputLines.forEach { l ->
        when {
            l == "$ cd /" -> Unit
            l == "$ cd .." -> currentDir = currentDir.parent!!
            l.startsWith("$ cd") -> Dir(l.removePrefix("$ cd "), currentDir)
                .also { dirs.add(it); currentDir.children.add(it); currentDir = it }
            l[0].isDigit() -> l.split(' ')
                .also { currentDir.files[it[1]] = it[0].toInt() }
        }
    }

    part1 {
        dirs.filter { it.size <= 100000 }.sumOf { it.size }
    }

    part2 {
        val mustFreeUp = 30000000 - (70000000 - dirs.first().size)
        dirs.filter { it.size >= mustFreeUp }.minOf { it.size }
    }

    expectPart1 = 95437
    expectPart2 = 24933642
}

data class Dir(val name: String, val parent: Dir?) {
    val children = mutableSetOf<Dir>()
    val files = mutableMapOf<String, Int>()

    val size: Int by lazy {
        files.values.sum() + children.sumOf { it.size }
    }
}

// optional printDir function, for debugging directory structures
// NOT required for the solution

fun printDir(dir: Dir, indent: Int = 0) {
    println("${" ".repeat(indent)}- ${dir.name} (dir)")
    dir.children.forEach { printDir(it, indent + 2) }
    dir.files.forEach {
        println("${" ".repeat(indent)}- ${it.key} (file, size = ${it.value})")
    }
}
