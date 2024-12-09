fun main() {

    fun getSB(input: String, fakeChar: Char): Pair<StringBuilder, Int> {
        val sb = StringBuilder()
        var isFreeSpace = false
        var i = 0
        for (char in input) {
            when {
                isFreeSpace -> { repeat(char - '0') { sb.append(fakeChar) } }
                else -> {
                    repeat(char - '0') { sb.append(Char(i)) }
                    i++
                }
            }
            isFreeSpace = !isFreeSpace
        }
        return sb to i
    }

    fun part1(input: String): Long {
        val fakeChar = Char(input.length)
        var (sb, _) = getSB(input, fakeChar)

        var start = 0
        var end = sb.length - 1
        while (start < end) {
            while (sb[start] != fakeChar) start++
            while (sb[end] == fakeChar) end--
            sb[start] = sb[end]
            sb[end] = fakeChar
            start++
            end--
        }
        return sb.withIndex().sumOf { (i, c) -> if (c != fakeChar) i * c.code.toLong() else 0 }
    }

    fun part2(input: String): Long {
        val fakeChar = Char(input.length)
        var (sb, i) = getSB(input, fakeChar)

        for (j in i - 1 downTo 0) {
            val segmentStart = sb.indexOfFirst { it == Char(j) }
            val segmentLength = sb.count { it == Char(j) }
            val freeSpaceStart = sb.indexOf(fakeChar.toString().repeat(segmentLength))
            if (freeSpaceStart == -1 || freeSpaceStart > segmentStart) continue
            sb = sb.replace(freeSpaceStart, freeSpaceStart + segmentLength, Char(j).toString().repeat(segmentLength))
            sb = sb.replace(segmentStart, segmentStart + segmentLength , fakeChar.toString().repeat(segmentLength))
        }

        return sb.withIndex().sumOf { (i, c) -> if (c != fakeChar) i * c.code.toLong() else 0 }
    }

    val testInput = readInput("testInput")
    check(part1(testInput) == 1928L) { "wrong part 1! returned ${part1(testInput)}" }
    val testInput2 = readInput("testInput2")
    check(part2(testInput2) == 2858L) { "wrong part 2! returned ${part2(testInput2)}" }
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}