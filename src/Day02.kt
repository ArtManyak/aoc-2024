import kotlin.math.abs
import kotlin.math.sign

fun main() {
    fun isSafe(numbers: List<Int>): Boolean {
        val sign = (numbers[0] - numbers[1]).sign
        for (i in 0 until numbers.size - 1) {
            val diff = abs(numbers[i] - numbers[i + 1])
            if (diff < 1 || diff > 3) return false
            val newSign = (numbers[i] - numbers[i + 1]).sign
            if (newSign != sign) return false
        }
        return true
    }

    fun isSafe(numbers: List<Int>, withSkip: Boolean): Boolean {
        if (!withSkip) return isSafe(numbers)
        for (iToSkip in 0 until numbers.size) {
            if (isSafe(numbers.filterIndexed { i, _ -> i != iToSkip })) return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        return input.map { it.split(" ").map { it.toInt() } }.count { isSafe(it, false) }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(" ").map { it.toInt() } }.count { isSafe(it, true) }
    }

    val testInput = readInput("testInput")
    check(part1(testInput) == 2) { "wrong part 1! returned ${part1(testInput)}" }
    check(part2(testInput) == 4) { "wrong part 2! returned ${part2(testInput)}" }
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
