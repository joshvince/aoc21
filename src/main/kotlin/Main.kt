package adventofcode21

fun main(args: Array<String>) {
    println("Lets solve the puzzle...")
    val inputFilePath: String = args.first()

    val input = InputParser().csvToIntList(inputFilePath)
    val result = Day1().solvePartTwo(input)

    println("This answer is: $result")
}
