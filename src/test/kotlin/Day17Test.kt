import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

@DisplayName("Day 17")
class Day17Test {

    private val sampleInput = readText("Day17-sample.txt")
    private val actualInput = readText("Day17.txt")
    private val rocksInput = readText("Day17-rocks.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 3_068,
            actual = Day17(sampleInput, rocksInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 3_065,
            actual = Day17(actualInput, rocksInput).part1(),
        )

    }

    @Ignore
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 1_514_285_714_288L,
            actual = Day17(sampleInput, rocksInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 0,
            actual = Day17(actualInput, rocksInput).part2(),
        )

    }

}