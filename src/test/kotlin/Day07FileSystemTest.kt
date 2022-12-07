import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.test.assertEquals

@DisplayName("Day 07")
@DisabledOnOs(OS.WINDOWS)
class Day07FileSystemTest {

    private val sampleInput = readLines("Day07-sample.txt")
    private val actualInput = readLines("Day07.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1OnFileSystem {

        @Test
        fun `Matches example`(@TempDir root: Path) =
            assertEquals(
                expected = 95437,
                actual = Day07FileSystem(sampleInput, root).part1(),
            )

        @Test
        fun `Actual answer`(@TempDir root: Path) = assertEquals(
            expected = 1084134,
            actual = Day07FileSystem(actualInput, root).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2OnFileSystem {

        @Test
        fun `Matches example`(@TempDir root: Path) = assertEquals(
            expected = 24933642,
            actual = Day07FileSystem(sampleInput, root).part2(),
        )

        @Test
        fun `Actual answer`(@TempDir root: Path) = assertEquals(
            expected = 6183184,
            actual = Day07FileSystem(actualInput, root).part2(),
        )

    }

}