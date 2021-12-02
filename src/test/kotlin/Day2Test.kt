import adventofcode21.Day2
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

internal class Day2Test {
    private val sampleInput = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
    private val describedClass = Day2()

    @Test
    @DisplayName("It solves the part one sample")
    fun solvesPartOneSampleInput() {
        assertThat(describedClass.solvePartOne(sampleInput)).isEqualTo(150)
    }

    @Test
    @DisplayName("It solves the part two sample")
    fun solvesPartTwoSampleInput() {
        assertThat(describedClass.solvePartTwo(sampleInput)).isEqualTo(900)
    }
}
