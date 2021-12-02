package adventofcode21

class Day2() {
    private lateinit var instructions: List<Instruction>

    fun solvePartOne(input: List<String>): Int {
        instructions = parseInput(input)

        val endPosition = instructions.fold(Position(0,0,0)) {
                acc, instruction -> updatePosition(acc, instruction)
        }

        return endPosition.x * endPosition.y
    }

    fun solvePartTwo(input: List<String>): Int {
        instructions = parseInput(input)
        val endPosition = instructions.fold(Position(0,0,0)) {
                acc, instruction -> accountForAim(acc, instruction)
        }

        return endPosition.x * endPosition.y
    }

    private fun updatePosition(position: Position, instruction: Instruction): Position {
        when (instruction.direction) {
            Direction.FORWARD -> position.x = position.x + instruction.units
            Direction.DOWN -> position.y = position.y + instruction.units
            Direction.UP -> position.y = position.y - instruction.units
        }
        return position
    }

    private fun accountForAim(position: Position, instruction: Instruction): Position {
        var newPosition = position
        when (instruction.direction) {
            Direction.FORWARD -> newPosition = handleForwardInstruction(position, instruction)
            Direction.DOWN -> newPosition.aim = position.aim + instruction.units
            Direction.UP -> newPosition.aim = position.aim - instruction.units
        }
        return newPosition
    }

    private fun handleForwardInstruction(position: Position, instruction: Instruction): Position {
        val newXPos = position.x + instruction.units
        val newYPos = position.y + (position.aim * instruction.units)

        return Position(newXPos, newYPos, position.aim)
    }

    private fun parseInput(lines: List<String>): List<Instruction> {
        return lines.map {
            val (direction, unit) = it.split(" ")
            Instruction(Direction.valueOf(direction.uppercase()), unit.toInt())
        }
    }
}

data class Instruction(val direction: Direction, val units: Int)

enum class Direction { FORWARD, DOWN, UP }

data class Position(var x: Int, var y: Int, var aim: Int)
