package adventofcode21

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class InputParser() {
    fun fileToIntList(filePath: String): List<Int> {
        val file = File(filePath)
        val rows: List<List<String>> = csvReader().readAll(file)

        return rows.map { it.first().toInt() }
    }

    fun fileToStringList(filePath: String): List<String> {
        return File(filePath).readLines()
    }
}
