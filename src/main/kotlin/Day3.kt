package adventofcode21

class Day3(input: List<String>) {
    companion object {
        const val inputFileName: String = "day3.csv"
    }

    private val bitLines: List<List<Int>> = input.map { line -> line.map { it.digitToInt() } }

    fun solvePartOne(): Int {
        val mostCommonBits: MutableList<Int> = emptyList<Int>().toMutableList()
        val leastCommonBits: MutableList<Int> = emptyList<Int>().toMutableList()

        for (i in 0 until bitLines.first().size) {
            val (leastCommonBit, mostCommonBit) = getBitByFrequency(i)

            mostCommonBits.add(mostCommonBit.bit)
            leastCommonBits.add(leastCommonBit.bit)
        }

        return convertToDecimal(mostCommonBits) * convertToDecimal(leastCommonBits)
    }

    fun solvePartTwo(): Int {
        val oxygenGeneratorRating = calculateLifeSupportComponent(LifeSupportComponent.OXYGEN_GENERATOR, bitLines, 0)
        val co2ScrubberRating = calculateLifeSupportComponent(LifeSupportComponent.CO2_SCRUBBER, bitLines, 0)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    private fun calculateLifeSupportComponent(
        component: LifeSupportComponent,
        numbers: List<List<Int>>,
        pos: Int
    ): Int {
        if (numbers.size == 1) return convertToDecimal(numbers.single())

        val (leastCommonBit, mostCommonBit) = getBitByFrequency(pos, numbers)

        val bitCriteria = if (component == LifeSupportComponent.OXYGEN_GENERATOR) {
            if(leastCommonBit.frequency == mostCommonBit.frequency) 1 else mostCommonBit.bit
        } else {
            if(leastCommonBit.frequency == mostCommonBit.frequency) 0 else leastCommonBit.bit
        }

        val numbersWithBitAtPosition = numbers.filter { it[pos] == bitCriteria }

        return calculateLifeSupportComponent(component, numbersWithBitAtPosition, pos + 1)

    }

    private fun getBitByFrequency(pos: Int, numbers: List<List<Int>> = bitLines): Array<BitFrequency> {
        val bitsAtPosition: List<Int> = numbers.map { it[pos] }
        val (leastCommon, mostCommon) = bitsAtPosition.groupingBy { it }.eachCount()
                .toList().sortedBy { (_bit, frequency) -> frequency }

        return arrayOf(
            BitFrequency(leastCommon.first, leastCommon.second),
            BitFrequency(mostCommon.first, mostCommon.second)
        )
    }

    private fun convertToDecimal(bitArray: List<Int>): Int = bitArray.joinToString("").toInt(2)
}

data class BitFrequency(val bit: Int, val frequency: Int)

enum class LifeSupportComponent { CO2_SCRUBBER, OXYGEN_GENERATOR }
