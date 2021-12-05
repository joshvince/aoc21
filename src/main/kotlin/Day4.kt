package adventofcode21

class Day4(input: List<String>) {
    companion object {
        const val inputFileName: String = "day4.csv"
    }

    private val numbersCalled: List<Int> = input.first().split(",").map { it.toInt() }
    private var boards: MutableList<Board> = mapInputToBoards(input).toMutableList()
    private var boardsWithWins: MutableList<Board> = mapInputToBoards(input).toMutableList()

    private fun mapInputToBoards(inputStrings: List<String>): List<Board> {
        val onlyBoards = inputStrings.drop(1).filterNot { it == "" }
        val boardInputs: List<List<Int>> = onlyBoards.map { row ->
            row.split("\\s+".toRegex()).filterNot { it == ""}.map { it.toInt()}
        }

        return boardInputs.chunked(5).map { Board(it) }
    }

    fun solvePartOne(): Int? {
        numbersCalled.forEach { number -> boards.forEach { it.mark(number) } }
        val allWinners: List<Victory> = boards.map { it.victories }.flatten()

        return allWinners.minByOrNull { it.numberCount }?.score
    }

    fun solvePartTwo(): Int? {
        var positionToCall = 0

        while (boards.isNotEmpty()) {
            boards.forEach { it.mark(numbersCalled[positionToCall]) }

            boards.filter { it.victories.isNotEmpty() }.forEach { board ->
                boardsWithWins.add(board)
            }
            boards.removeIf { it.victories.isNotEmpty() }
            positionToCall ++
        }
        return boardsWithWins.last().victories.last().score
    }
}

class Board(input: List<List<Int>>) {
    var victories: MutableList<Victory> = emptyList<Victory>().toMutableList()
    private var rows: MutableList<MutableList<Square>> = input.map { convertInputToRow(it) }.toMutableList()
    private var numberCounter: Int = 0
    private var numbersMarked: MutableList<Int> = emptyList<Int>().toMutableList()

    private fun convertInputToRow(list: List<Int>): MutableList<Square> {
        return list.mapIndexed { index, number ->
            Square(index, number)
        }.toMutableList()
    }

    fun mark(number: Int) {
        numbersMarked.add(number)
        numberCounter++
        rows.forEachIndexed { rowIndex, row ->
            val indexWithNumber: Int? = row.find { it.value == numbersMarked.last() }?.rowPosition
            if (indexWithNumber is Int && !rows[rowIndex][indexWithNumber].marked) {
                rows[rowIndex][indexWithNumber].marked = true
                checkAndRegisterWinner(rowIndex, indexWithNumber)
            }
        }
    }

    private fun checkAndRegisterWinner(rowPosition: Int, columnPosition: Int) {
        val row = rows[rowPosition]
        val column = rows.map { it[columnPosition] }

        if (row.all { it.marked } || column.all{ it.marked }) {
            victories.add(Victory(score(), numberCounter))
        }
        if (column.all{ it.marked }) {
            victories.add(Victory(score(), numberCounter))
        }
    }

    private fun score(): Int {
        val unmarkedSquareTotal: Int = rows.flatten().filterNot { it.marked }.sumOf { it.value }
        return unmarkedSquareTotal * numbersMarked.last()
    }
}

data class Square(val rowPosition: Int, val value: Int, var marked: Boolean = false)
data class Victory(val score: Int, val numberCount: Int)
