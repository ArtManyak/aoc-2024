fun main() {

    fun countTrails(lines: List<String>, start: Point, checkWas: Boolean): Int {
        val points = mutableListOf(start)
        val was = mutableSetOf(start)
        var ans = 0
        while (points.isNotEmpty()) {
            val curPoint = points.removeAt(0)
            if (lines[curPoint] == '9') {
                ans++
                continue
            }
            for (dir in directions4) {
                val nextPoint = curPoint + dir
                if (nextPoint.isWithinBounds(lines) && lines[nextPoint] - lines[curPoint] == 1 && !(checkWas && nextPoint in was)) {
                    points.add(nextPoint)
                    was.add(nextPoint)
                }
            }
        }
        return ans
    }

    fun part1(lines: List<String>): Int {
        return lines.indices.sumOf { i ->
            lines[i].indices.sumOf { j ->
                if (lines[i][j] == '0') countTrails(lines, Point(i, j), true) else 0
            }
        }
    }


    fun part2(lines: List<String>): Int {
        return lines.indices.sumOf { i ->
            lines[i].indices.sumOf { j ->
                if (lines[i][j] == '0') countTrails(lines, Point(i, j), false) else 0
            }
        }
    }

    val testInput = readLines("testInput")
    check(part1(testInput) == 36) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")
    check(part2(testInput2) == 81) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")
    part1(input).println()
    part2(input).println()
}