import adventofcode21.Day1
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

internal class Day1Test {
    private val sampleInput = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    private val describedClass = Day1()

    @Test
    @DisplayName("It solves the part one sample")
    fun solvesPartOneSampleInput() {
        assertThat(describedClass.solvePartOne(sampleInput)).isEqualTo(7)
    }

    @Test
    @DisplayName("It solves the part two sample")
    fun solvesPartTwoSampleInput() {
        assertThat(describedClass.solvePartTwo(sampleInput)).isEqualTo(5)
    }
}
