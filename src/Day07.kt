fun main() {

    fun checkLine(line: String, withConcat: Boolean): Long {
        val result = line.split(": ")[0].toLong()
        val numbers = line.split(": ")[1].split(" ").map { it.toLong() }

        var allPossibleResults = mutableSetOf<Long>(numbers[0])
        for (i in 1 until numbers.size) {
           val currentResults = mutableSetOf<Long>()
           for (possibleResult in allPossibleResults) {
               currentResults.add(possibleResult + numbers[i])
               currentResults.add(possibleResult * numbers[i])
               if (withConcat) currentResults.add("$possibleResult${numbers[i]}".toLong())
           }
           allPossibleResults = currentResults
        }

        return if (result in allPossibleResults) result else 0L
    }

    fun part1(input: List<String>): Long {
        return input.sumOf { checkLine(it, false) }
    }


    fun part2(input: List<String>): Long {
        return input.sumOf { checkLine(it, true) }
    }

    val testInput = readLines("testInput")
    check(part1(testInput) == 3749L) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")
    check(part2(testInput2) == 11387L) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")
    part1(input).println()
    part2(input).println()
}
