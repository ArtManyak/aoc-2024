fun main() {

    fun parseLine(line: String, sign: Char): Pair<Long, Long> {
        val firstIndex = line.indexOfFirst { it == sign }
        val lastIndex = line.indexOfLast { it == sign }
        val x = line.substring(firstIndex + 1, line.indexOf(',', firstIndex)).toLong()
        val y = line.substring(lastIndex + 1).toLong()
        return x to y
    }

    fun solveEquations(
        a1: Long, b1: Long, c1: Long,
        a2: Long, b2: Long, c2: Long
    ): Pair<Long, Long>? {
        val determinant = a1 * b2 - a2 * b1
        if (determinant == 0L) return null

        val x = (c1 * b2 - c2 * b1) / determinant
        val y = (a1 * c2 - a2 * c1) / determinant

        return Pair(x, y).takeIf { (a1 * x + b1 * y == c1) && (a2 * x + b2 * y == c2) }
    }

    fun solve(input: List<String>, isFirst: Boolean): Long {
        var ans = 0L
        for (part in input) {
            val (buttonA, buttonB, prizeTmp) = part.lines().mapIndexed { index, line ->
                parseLine(line, if (index < 2) '+' else '=')
            }
            val toAdd = if (!isFirst) 10000000000000 else 0
            val prize = Pair(prizeTmp.first + toAdd, prizeTmp.second + toAdd)

            solveEquations(buttonA.first, buttonB.first, prize.first, buttonA.second, buttonB.second, prize.second)?.let {
                if (!isFirst || (it.first <= 100 && it.second <= 100)) {
                    ans += it.first * 3 + it.second
                }
            }
        }
        return ans
    }

    fun part1(input: List<String>): Long {
        return solve(input, true)
    }

    fun part2(input: List<String>): Long {
        return solve(input, false)
    }

    val testInput = readInput("testInput").split("\n\n")
    check(part1(testInput) == 480L) { "wrong part 1! returned ${part1(testInput)}" }
//    val testInput2 = readInput("testInput2").split("\n\n")
//    check(part2(testInput2) == 1206L) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readInput("input").split("\n\n")
    part1(input).println()
    part2(input).println()
}