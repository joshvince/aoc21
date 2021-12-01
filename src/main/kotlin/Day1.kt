package adventofcode21

class Day1() {
    private lateinit var puzzleInput: List<Int>
    private var depthIncreases: Int = 0
    private var lastDepth: Int = 0

    fun solvePartOne(input: List<Int>): Int {
        puzzleInput = input
        depthIncreases = 0
        lastDepth = puzzleInput.first()

        puzzleInput.forEach {
            if(it > lastDepth) depthIncreases++
            lastDepth = it
        }

        return depthIncreases
    }

    fun solvePartTwo(input: List<Int>): Int {
        puzzleInput = input
        depthIncreases = 0
        lastDepth = calculateDepthWindow(0, puzzleInput.first())

        for((index, value) in puzzleInput.withIndex()) {
            if (index > (puzzleInput.size - 3)) break

            val newDepthWindow = calculateDepthWindow(index, value)
            if(newDepthWindow > lastDepth) depthIncreases++
            lastDepth = newDepthWindow
        }

        return depthIncreases
    }

    private fun calculateDepthWindow(index: Int, item: Int): Int {
        val window = Triple(item, puzzleInput[index + 1], puzzleInput[index + 2])
        return window.toList().sum()
    }
}
