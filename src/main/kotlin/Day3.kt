package adventofcode21

class Day3(input: List<String>) {
    companion object {
        const val inputFileName: String = "day3.csv"
    }

    private val bitLines: List<List<Int>> = input.map { convertToBits(it) }

    fun solvePartOne(): Int {
        val mostCommonBits: MutableList<Int> = emptyList<Int>().toMutableList()
        val leastCommonBits: MutableList<Int> = emptyList<Int>().toMutableList()

        for (i in 0 until bitLines.first().size) {
            val (leastCommonBit, mostCommonBit) = getBitByFrequency(i)

            mostCommonBits.add(mostCommonBit.bit)
            leastCommonBits.add(leastCommonBit.bit)
        }

        val gammaRate = convertToDecimal(mostCommonBits)
        val epsilonRate = convertToDecimal(leastCommonBits)

        return gammaRate * epsilonRate
    }

    fun solvePartTwo(): Int {
        val oxygenGeneratorRating = oxygenGeneratorRating(bitLines, 0)
        val co2ScrubberRating = co2ScrubberRating(bitLines, 0)

        return convertToDecimal(oxygenGeneratorRating) * convertToDecimal(co2ScrubberRating)
    }

    private fun oxygenGeneratorRating(numbers: List<List<Int>>, pos: Int): List<Int> {
        if (numbers.size == 1) return numbers.single()

        val (leastCommonBit, mostCommonBit) = getBitByFrequency(pos, numbers)
        val bitCriteria = if(leastCommonBit.frequency == mostCommonBit.frequency) 1 else mostCommonBit.bit

        val numbersWithBitAtPosition = numbers.filter { it[pos] == bitCriteria }

        return oxygenGeneratorRating(numbersWithBitAtPosition, pos + 1)
    }

    private fun co2ScrubberRating(numbers: List<List<Int>>, pos: Int): List<Int> {
        if (numbers.size == 1) return numbers.single()

        val (leastCommonBit, mostCommonBit) = getBitByFrequency(pos, numbers)
        val bitCriteria = if(leastCommonBit.frequency == mostCommonBit.frequency) 0 else leastCommonBit.bit

        val numbersWithBitAtPosition = numbers.filter { it[pos] == bitCriteria }

        return co2ScrubberRating(numbersWithBitAtPosition, pos + 1)
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

    private fun convertToBits(line: String): List<Int> = line.map { it.digitToInt() }
}

data class BitFrequency(val bit: Int, val frequency: Int)
