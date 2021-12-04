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

            mostCommonBits.add(mostCommonBit)
            leastCommonBits.add(leastCommonBit)
        }

        val gammaRate = convertToDecimal(mostCommonBits)
        val epsilonRate = convertToDecimal(leastCommonBits)

        return gammaRate * epsilonRate
    }

    private fun getBitByFrequency(pos: Int): Pair<Int, Int> {
        val bitsAtPosition: List<Int> = bitLines.map { it[pos] }
        val (leastCommon, mostCommon) = bitsAtPosition.groupingBy { it }.eachCount()
                .toList().sortedBy { (_bit, frequency) -> frequency }

        return Pair(leastCommon.first, mostCommon.first)
    }

    private fun convertToDecimal(bitArray: List<Int>): Int = bitArray.joinToString("").toInt(2)

    private fun convertToBits(line: String): List<Int> = line.map { it.digitToInt() }
}
