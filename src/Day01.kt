import kotlin.math.abs

private fun getSortedLists(input: List<String>): Pair<List<Int>, List<Int>> {
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    for (line in input) {
        val (n1, n2) = line.split(" ").filter { !it.isEmpty() }.map { it.toInt() }
        list1.add(n1)
        list2.add(n2)
    }
    list1.sort()
    list2.sort()
    return Pair(list1, list2)
}

fun main() {
    fun part1(input: List<String>): Int {
        val (list1, list2) = getSortedLists(input)
        return list1.zip(list2).sumOf { (n1, n2) -> abs(n1 - n2) }
    }

    fun part2(input: List<String>): Int {
        val (list1, list2) = getSortedLists(input)
        return list1.sumOf { n1 ->
            list2.count { n2 -> n1 == n2 } * n1
        }
    }

    val testInput = readInput("testInput")
    check(part1(testInput) == 11) { "wrong part 1!" }
    check(part2(testInput) == 31) { "wrong part 2!" }
    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
