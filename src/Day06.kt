fun main() {

    fun findStart(input: List<String>): Pair<Int, Int> {
        val i = input.indexOfFirst {
            it.contains("^")
        }
        return i to input[i].indexOfFirst { it == '^' }
    }

    fun part1(input: List<String>): Int {
        var (i, j) = findStart(input)
        var directionIndex = 0
        var visited = setOf(i to j)
        while (i in input.indices && j in input[i].indices) {
            val (di, dj) = directions4[directionIndex]
            if (i+di !in input.indices || j+dj !in input[i].indices) break
            if (input[i+di][j+dj] == '#') {
                directionIndex = (directionIndex + 1) % 4
                continue
            }
            i += di
            j += dj
            visited = visited + (i to j)
        }
        return visited.size
    }


    fun part2(input: List<String>): Int {
        var (i, j) = findStart(input)
        var directionIndex = 0
        var visited = setOf(i to j to directionIndex)
        var newBlocks = setOf<Pair<Int, Int>>()
        while (i in input.indices && j in input[i].indices) {
            val (di, dj) = directions4[directionIndex]
            if (i+di !in input.indices || j+dj !in input[i].indices) break
            if (input[i+di][j+dj] == '#') {
                directionIndex = (directionIndex + 1) % 4
                continue
            }

            var tempDirectionIndex = (directionIndex + 1) % 4
            val (tempDi, tempDj) = directions4[tempDirectionIndex]
            var isBlockWorks = false
            var k = 1
            while (i + k*tempDi in input.indices && j + k*tempDj in input[i].indices) {
                if (visited.contains(i + k*tempDi to j + k*tempDj to tempDirectionIndex)) {
                    isBlockWorks = true
                    break
                }
                k++
            }
            if (isBlockWorks) newBlocks = newBlocks + (i + di to j + dj)


            i += di
            j += dj
            visited = visited + (i to j to directionIndex)
        }
        return newBlocks.size
    }

    val testInput = readLines("testInput")
    check(part1(testInput) == 41) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readLines("testInput2")
    check(part2(testInput2) == 6) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readLines("input")
    part1(input).println()
    part2(input).println()
}
