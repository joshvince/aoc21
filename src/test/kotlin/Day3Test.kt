import adventofcode21.Day3
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

internal class Day3Test {
    private val sampleInput = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )
    private val describedClass = Day3(sampleInput)

    @Test
    @DisplayName("It solves the part one sample")
    fun solvesPartOneSampleInput() {
        assertThat(describedClass.solvePartOne()).isEqualTo(198)
    }

}
