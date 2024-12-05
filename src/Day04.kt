fun main() {

    fun part1(input: List<String>): Int {
        fun countXmasFrom(i: Int, j: Int): Int {
            return directions8.count { (di, dj) ->
                (0 until 4).mapNotNull { step ->
                    val ni = i + di * step
                    val nj = j + dj * step
                    if (ni in input.indices && nj in input[ni].indices) input[ni][nj] else null
                }.joinToString("") == "XMAS"
            }
        }
        return input.indices.sumOf { i ->
            input[i].indices.sumOf { j ->
                countXmasFrom(i, j)
            }
        }
    }


    fun part2(input: List<String>): Int {
        fun isXmasFrom(i: Int, j: Int): Boolean {
            val diagonals = listOf(
                listOf(i - 1 to j - 1, i to j, i + 1 to j + 1),
                listOf(i - 1 to j + 1, i to j, i + 1 to j - 1)
            )
            return diagonals.all { diag ->
                diag.map {
                    if (it.first !in input.indices || it.second !in input[it.first].indices) return false
                    input[it.first][it.second]
                }.joinToString("") in listOf("MAS", "SAM")
            }
        }
        return input.indices.sumOf { i ->
            input[i].indices.count { j ->
                isXmasFrom(i, j)
            }
        }
    }

    val testInput = readLines("testInput")
    check(part1(testInput) == 18) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")
    check(part2(testInput2) == 9) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")
    part1(input).println()
    part2(input).println()
}
