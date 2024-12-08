fun main() {

    fun getGroups(input: List<String>): Map<Char, List<Point>> {
        return input.flatMapIndexed { i, row ->
            row.mapIndexed { j, char -> char to Point(i, j) }
        }.filter { it.first != '.' }
            .groupBy({ it.first }, { it.second })
    }
    fun part1(input: List<String>): Int {
        val groups = getGroups(input)

        val antinodes = mutableSetOf<Point>()
        for (points in groups.values) {
            for (i in points.indices) {
                for (j in i+1 until points.size) {
                    val p1 = points[i]
                    val p2 = points[j]

                    listOf(
                        Point(p1.i - (p2.i - p1.i), p1.j - (p2.j - p1.j)),
                        Point(p2.i - (p1.i - p2.i), p2.j - (p1.j - p2.j))
                    ).filter { it.isWithinBounds(input) }
                        .let { antinodes.addAll(it) }
                }
            }
        }
        return antinodes.size
    }

    fun part2(input: List<String>): Int {
        val groups = getGroups(input)

        val antinodes = mutableSetOf<Point>()
        for (points in groups.values) {
            for (i in points.indices) {
                for (j in i+1 until points.size) {
                    val p1 = points[i]
                    val p2 = points[j]
                    antinodes += p1
                    antinodes += p2
                    var k = 1
                    while (true) {
                        var added = false
                        val antinode1 = Point(p1.i - (p2.i - p1.i)*k, p1.j - (p2.j - p1.j)*k)
                        val antinode2 = Point(p2.i - (p1.i - p2.i)*k, p2.j - (p1.j - p2.j)*k)
                        if (antinode1.isWithinBounds(input)) {
                            added = true
                            antinodes += antinode1
                        }
                        if (antinode2.isWithinBounds(input)) {
                            added = true
                            antinodes += antinode2
                        }
                        if (!added) break
                        k++
                    }
                }
            }
        }
        return antinodes.size

    }

    val testInput = readLines("testInput")
    check(part1(testInput) == 14) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")
    check(part2(testInput2) == 34) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")
    part1(input).println()
    part2(input).println()
}
