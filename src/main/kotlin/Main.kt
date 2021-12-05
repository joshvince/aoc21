package adventofcode21

fun main(args: Array<String>) {
    println("Lets solve the puzzle...")
    val inputFilePath = "src/main/resources/${Day4.inputFileName}"

    val input = InputParser().fileToStringList(inputFilePath)
    val result = Day4(input).solvePartTwo()

    println("This answer is: $result")
}
