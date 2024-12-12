fun main() {

    fun search(input: List<String>, start: Point, was: MutableSet<Point>): Long {
        val points = mutableListOf(start)
        was += start
        var count = 1L
        var perimeter = 0L
        while (points.isNotEmpty()) {
            val curPoint = points.removeAt(0)
            for (dir in directions4) {
                val nextPoint = curPoint + dir
                if (!nextPoint.isWithinBounds(input) || input[nextPoint] != input[start]) {
                    perimeter++
                    continue
                }
                if (nextPoint !in was) {
                    points.add(nextPoint)
                    was.add(nextPoint)
                    count++
                }
            }
        }
        return count * perimeter
    }

    fun part1(input: List<String>): Long {
        val was = mutableSetOf<Point>()
        var ans = 0L
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (Point(i, j) !in was) ans += search(input, Point(i, j), was)
            }
        }
        return ans
    }

    fun search2(
        input: List<String>,
        start: Point,
        was: MutableSet<Point>,
        borders: MutableMap<Point, MutableSet<Point>>
    ): Long {
        val points = mutableListOf(start)
        was += start
        var count = 1L
        var perimeter = 0L
        fun needBorder(point: Point): Boolean {
            return !point.isWithinBounds(input) || input[point] != input[start]
        }
        while (points.isNotEmpty()) {
            val curPoint = points.removeAt(0)
            for (dir in directions4) {
                val nextPoint = curPoint + dir
                if (needBorder(nextPoint)) {
                    var addPerimeter = true
                    when (dir) {
                        UP, DOWN -> {
                            val prevPoint1 = curPoint + LEFT
                            val prevPoint2 = curPoint + RIGHT
                            if (needBorder(prevPoint1 + dir) && borders[prevPoint1]?.contains(dir) == true && input[prevPoint1] == input[curPoint]) addPerimeter = false
                            if (needBorder(prevPoint2 + dir) && borders[prevPoint2]?.contains(dir) == true && input[prevPoint2] == input[curPoint]) addPerimeter = false
                        }
                        RIGHT, LEFT -> {
                            val prevPoint1 = curPoint + UP
                            val prevPoint2 = curPoint + DOWN
                            if (needBorder(prevPoint1 + dir) && borders[prevPoint1]?.contains(dir) == true && input[prevPoint1] == input[curPoint]) addPerimeter = false
                            if (needBorder(prevPoint2 + dir) && borders[prevPoint2]?.contains(dir) == true && input[prevPoint2] == input[curPoint]) addPerimeter = false
                        }
                    }
                    borders.getOrPut(curPoint) { mutableSetOf() }.add(dir)
                    if (addPerimeter) perimeter++
                    continue
                }
                if (nextPoint !in was) {
                    points.add(nextPoint)
                    was.add(nextPoint)
                    count++
                }
            }
        }
        return count * perimeter
    }

    fun part2(input: List<String>): Long {
        val was = mutableSetOf<Point>()
        val borders = mutableMapOf<Point, MutableSet<Point>>()
        var ans = 0L
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (Point(i, j) !in was) ans += search2(input, Point(i, j), was, borders)
            }
        }
        return ans
    }

    val testInput = readLines("testInput")
    check(part1(testInput) == 1930L) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")
    check(part2(testInput2) == 1206L) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")
    part1(input).println()
    part2(input).println()
}