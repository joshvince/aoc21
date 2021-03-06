import adventofcode21.Day4
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

internal class Day4Test {
    private val sampleInput = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
//        "7,4,9,5,11,17,23,2,0,14,21,24",
//        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12",
        "",
        "22 13 17 11  0",
        "8  2 23  4 24",
        "21  9 14 16  7",
        "6 10  3 18  5",
        "1 12 20 15 19",
        "",
        "3 15  0  2 22",
        "9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7"
    )
    private val describedClass = Day4(sampleInput)

    @Test
    @DisplayName("It solves the part one sample")
    fun solvesPartOneSampleInput() {
        assertThat(describedClass.solvePartOne()).isEqualTo(4512)
    }

    @Test
    @DisplayName("It solves the part two sample")
    fun solvesPartTwoSampleInput() {
        assertThat(describedClass.solvePartTwo()).isEqualTo(1924)
    }
}
