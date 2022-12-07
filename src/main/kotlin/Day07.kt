import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.div

class Day07(input: List<String>) {

    private val fs = FileSystem(input)

    fun part1() = fs.dirsWithSize()
        .filter { (_, size) -> size <= 100_000 }.values
        .sum()

    fun part2() = fs.dirsWithSize().let { dirs ->
        val minSpaceToFree = dirs.getValue(root) - 40_000_000
        dirs.filter { (_, size) -> size >= minSpaceToFree }.values
    }.min()

    private class FileSystem(input: List<String>) {
        private val files: MutableList<Pair<Path, Long>> = mutableListOf()

        init {
            var pwd: Path = root
            input.foldInPlace(this) { line ->
                when {
                    line == "\$ ls" -> Unit
                    line.startsWith("dir ") -> Unit
                    line.startsWith("\$ cd ") -> {
                        when (val dir = line.substringAfterLast(" ")) {
                            "/" -> pwd = root
                            ".." -> pwd = pwd.parent
                            else -> pwd /= dir
                        }
                    }

                    else -> line.split(" ").let { (size, name) -> files += pwd / name to size.toLong() }
                }
            }
        }

        fun dirsWithSize() = files.foldInPlace(mutableMapOf<Path, Long>()) { (file, size) ->
            file.parents().forEach { compute(it) { _, acc -> (acc ?: 0) + size } }
        }
    }

}

private val root: Path = Paths.get("/")
private fun Path.parents() = generateSequence(parent, Path::getParent)
