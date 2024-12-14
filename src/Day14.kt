fun main() {

    data class Robot(var x: Int, var y: Int, val vx: Int, val vy: Int) {
        fun move(width: Int, height: Int) {
            x += vx
            y += vy
            if (x < 0) x += width
            if (y < 0) y += height
            if (x >= width) x -= width
            if (y >= height) y -= height
        }

        fun getQuadrant(width: Int, height: Int): Int? {
            if (x == width / 2 || y == height / 2) return null

            if (x < width / 2) {
                if (y < height / 2) return 1
                return 4
            } else {
                if (y < height / 2) return 2
                return 3
            }
        }
    }

    fun parseRobot(line: String): Robot {
        val firstEq = line.indexOfFirst { it == '=' }
        val firstComma = line.indexOfFirst { it == ',' }
        val x = line.substring(firstEq + 1, firstComma).toInt()
        val y = line.substring(firstComma + 1, line.indexOf(' ')).toInt()

        val lastEq = line.indexOfLast { it == '=' }
        val lastComma = line.indexOfLast { it == ',' }
        val vx = line.substring(lastEq + 1, lastComma).toInt()
        val vy = line.substring(lastComma + 1).toInt()
        return Robot(x, y, vx, vy)
    }

    fun part1(input: List<String>, width: Int, height: Int): Int {
        val robots = input.map { parseRobot(it) }
        (1..100).forEach { _ ->
            robots.forEach { it.move(width, height) }
        }
        val robotsByQuadrant = robots.groupBy { it.getQuadrant(width, height) }.filter { it.key != null }
        return robotsByQuadrant.values.fold(1) { it, list -> it * list.size }
    }

    fun part2(input: List<String>, width: Int, height: Int): Int {
        val robots = input.map { parseRobot(it) }
        val stringBuilder = StringBuilder()
        for (i in 0 until height) {
            for (j in 0 until width) {
                stringBuilder.append(".")
            }
            stringBuilder.appendLine()
        }
        robots.forEach { stringBuilder[it.x + it.y * (width + 1)] = '#' }
        var moves = 0
        while (true) {
            moves++
            robots.forEach { it.move(width, height) }
            stringBuilder.clear()
            for (i in 0 until height) {
                for (j in 0 until width) {
                    stringBuilder.append(".")
                }
                stringBuilder.appendLine()
            }
            robots.forEach { stringBuilder[it.x + it.y * (width + 1)] = '#' }
        }
        return moves
    }

//    val testInput = readLines("testInput")
//    check(part1(testInput, 11, 7) == 12) { "wrong part 1! returned ${part1(testInput, 11, 7)}" }
//    val testInput2 = readLines("testInput2")
//    check(part2(testInput2, 11, 7) == 1206) { "wrong part 2! returned ${part2(testInput2, 11, 7)}" }
    val input = readLines("input")
    part1(input, 101, 103).println()
    part2(input, 101, 103).println()
}