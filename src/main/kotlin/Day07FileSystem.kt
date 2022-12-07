import java.io.File
import java.io.RandomAccessFile
import java.nio.file.Path
import kotlin.io.path.*

@OptIn(ExperimentalPathApi::class)
class Day07FileSystem(input: List<String>, private val root: Path) {

    init {
        var pwd = root
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

                else -> line.split(" ").let { (size, name) ->
                    val file = (pwd / name).toFile().also { it.parentFile.mkdirs() }
                    RandomAccessFile(file, "rw").setLength(size.toLong())
                }
            }
        }
    }

    fun part1(): Long = root.dirs()
        .map { it.toPath().dirLength() }.filter { it <= 100_000 }.sum()

    fun part2(): Long {
        val minSpaceToFree = root.dirLength() - 40_000_000
        return root.dirs().map { it.toPath().dirLength() }.filter { it >= minSpaceToFree }.min()
    }

    private fun Path.dirs() = toFile().walk().filter(File::isDirectory)
    private fun Path.dirLength() = walk().filter { it.isRegularFile() }.sumOf { it.fileSize() }

}