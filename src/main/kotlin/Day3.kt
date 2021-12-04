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
            val occurrences = getOccurencesAtPos(i)
            val mostCommonBit = occurrences.maxByOrNull { it.value }?.key ?: continue
            val leastCommonBit = occurrences.minByOrNull { it.value }?.key ?: continue

            mostCommonBits.add(mostCommonBit)
            leastCommonBits.add(leastCommonBit)
        }

        val gammaRate = mostCommonBits.joinToString("").toInt(2)
        val epsilonRate = leastCommonBits.joinToString("").toInt(2)

        return gammaRate * epsilonRate
    }

    private fun getOccurencesAtPos(pos: Int): Map<Int, Int> {
        val bitsAtPosition: List<Int> = bitLines.map { it[pos] }
        return bitsAtPosition.groupingBy { it }.eachCount()
    }

    private fun convertToBits(line: String): List<Int> = line.map { it.digitToInt() }
}
