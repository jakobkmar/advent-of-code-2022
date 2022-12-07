import java.nio.file.Path
import kotlin.io.path.Path

fun main() = day(7) {

    // ! DISCLAIMER:
    // ! this is the non-refactored version I wrote early in the morning
    // ! will improve later

    class Directory(
        val children: MutableSet<Path> = mutableSetOf(),
        val files: MutableList<Pair<String, Int>> = mutableListOf(),
    )

    var currentPath = Path("/")
    val directories = mutableMapOf(currentPath to Directory())

    fun currentDir() = directories[currentPath]!!

    val sizes = mutableMapOf<Path, Long>()

    fun sizeOfDir(path: Path): Long {
        if (sizes.containsKey(path)) {
            return sizes[path]!!
        }

        val thisDir = directories[path]!!
        val mySize = thisDir.files.sumOf { it.second }
        val summedSize = mySize + thisDir.children.sumOf { sizeOfDir(it) }
        sizes[path] = summedSize
        return summedSize
    }

    var lsMode = false

    inputLines.forEach { l ->
        if (l.startsWith("$")) {
            if (lsMode) lsMode = false

            val command = l.removePrefix("$ ")
            when (val prefix = command.take(2)) {
                "cd" -> {
                    currentPath = when (val loc = command.removePrefix(prefix).trim()) {
                        ".." -> currentPath.parent
                        "/" -> Path("/")
                        else -> currentPath.resolve(loc).also { currentDir().children.add(it) }
                    }
                    directories.putIfAbsent(currentPath, Directory())
                }
                "ls" -> lsMode = true
            }
        } else if (lsMode) {
            val (prefix, name) = l.split(' ')
            when (prefix) {
                "dir" -> {
                    val dirPath = currentPath.resolve(name.trim())
                    directories.putIfAbsent(dirPath, Directory())
                    currentDir().children.add(dirPath)
                }
                else -> {
                    currentDir().files.add(name to prefix.toInt())
                }
            }
        }
    }

    part1 {
        directories.keys.map { sizeOfDir(it) }
            .filter { it <= 100000 }.sum()
    }

    part2 {
        val mustFreeUp = 30000000 - (70000000 - sizeOfDir(Path("/")))

        directories.keys.map { sizeOfDir(it) }
            .filter { it >= mustFreeUp }.min()
    }

    expectPart1 = 95437L
    expectPart2 = 24933642L
}
