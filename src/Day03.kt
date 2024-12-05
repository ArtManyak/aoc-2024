fun main() {

    fun part1(input: String): Long {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
        return regex.findAll(input).sumOf {
            val (first, second) = it.destructured
            first.toLong() * second.toLong()
        }
    }

    fun part2(input: String): Long {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""")
        var enabled = true
        return regex.findAll(input).sumOf { match ->
            when {
                match.groups[1] != null && enabled -> {
                    val (first, second) = match.destructured
                    return@sumOf first.toLong() * second.toLong()
                }
                match.value == "don't()" -> enabled = false
                match.value == "do()" -> enabled = true
            }
            0L
        }
    }

    val testInput = readLines("testInput")[0]
    check(part1(testInput) == 161L) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")[0]
    check(part2(testInput2) == 48L) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")[0]
    part1(input).println()
    part2(input).println()
}
