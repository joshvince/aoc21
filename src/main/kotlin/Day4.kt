package adventofcode21

class Day4(input: List<String>) {
    companion object {
        const val inputFileName: String = "day4.csv"
    }

    private val numbersCalled: List<Int> = input.first().split(",").map { it.toInt() }
    private val boards: MutableList<Board> = mapInputToBoards(input).toMutableList()

    private fun mapInputToBoards(inputStrings: List<String>): List<Board> {
        val onlyBoards = inputStrings.drop(1).filterNot { it == "" }
        val boardInputs: List<List<Int>> = onlyBoards.map { row ->
            row.split("\\s+".toRegex()).filterNot { it == ""}.map { it.toInt()}
        }

        return boardInputs.chunked(5).map { Board(it) }
    }

    fun solvePartOne(): Int? {
        numbersCalled.forEach { number ->
            boards.forEach { it.mark(number) }

            val winningBoard = boards.find { it.isWinner() }
            if (winningBoard is Board) {
                return winningBoard.sumOfUnmarkedNumbers() * number
            }
        }
        return null
    }
}

class Board(input: List<List<Int>>) {
    var rows: MutableList<MutableList<Square>> = input.map { convertInputToRow(it) }.toMutableList()

    private fun convertInputToRow(list: List<Int>): MutableList<Square> {
        return list.mapIndexed { index, number ->
            Square(index, number)
        }.toMutableList()
    }

    fun mark(number: Int) {
        rows.forEachIndexed { rowIndex, row ->
            val indexWithNumber: Int? = row.find { it.value == number }?.rowPosition

            if (indexWithNumber is Int) {
                rows[rowIndex][indexWithNumber].marked = true
            }
        }
    }

    fun isWinner(): Boolean {
        val completeRow: MutableList<Square>? = rows.find { row -> row.all { it.marked } }
        if (completeRow != null) return true

        for (i in 0..4 ) {
            val column: List<Square> = rows.map { row -> row[i] }

            if (column.all { it.marked }) return true
        }
        return false
    }

    fun sumOfUnmarkedNumbers(): Int {
        return rows.flatten().filterNot { it.marked }.sumOf { it.value }
    }
}

data class Square(val rowPosition: Int, val value: Int, var marked: Boolean = false)
