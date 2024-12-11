fun main() {

    fun solve(input: String, totalCount: Int): Long {
        var numbers = input.split(" ")
            .map { it.toLong() }
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
            .toMutableMap()

        repeat(totalCount) {
            val newNumbers = mutableMapOf<Long, Long>()
            numbers.forEach { (number, count) ->
                val str = number.toString()
                when {
                    number == 0L -> newNumbers[1] = newNumbers.getOrDefault(1, 0) + count
                    str.length % 2 == 0 -> {
                        val (left, right) = str.chunked(str.length / 2).map { it.toLong() }
                        newNumbers[left] = newNumbers.getOrDefault(left, 0) + count
                        newNumbers[right] = newNumbers.getOrDefault(right, 0) + count
                    }
                    else -> {
                        val newValue = number * 2024
                        newNumbers[newValue] = newNumbers.getOrDefault(newValue, 0) + count
                    }
                }
            }
            numbers = newNumbers
        }

        return numbers.values.sum()
    }

    fun part1(input: String): Long {
        return solve(input, 25)
    }

    fun part2(input: String): Long {
        return solve(input, 75)
    }

    val testInput = readInput("testInput")
    check(part1(testInput) == 55312L) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readInput("testInput2")
    check(part2(testInput2) == 65601038650482L) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}