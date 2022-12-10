import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 10")
class Day10Test {

    private val sampleInput = readLines("Day10-sample.txt")
    private val actualInput = readLines("Day10.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 13140,
            actual = Day10(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 14760,
            actual = Day10(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
            """.trimIndent(),
            actual = Day10(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            // EFGERURE
            expected = """
                ####.####..##..####.###..#..#.###..####.
                #....#....#..#.#....#..#.#..#.#..#.#....
                ###..###..#....###..#..#.#..#.#..#.###..
                #....#....#.##.#....###..#..#.###..#....
                #....#....#..#.#....#.#..#..#.#.#..#....
                ####.#.....###.####.#..#..##..#..#.####.
            """.trimIndent(),
            actual = Day10(actualInput).part2(),
        )

    }

}