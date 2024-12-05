fun main() {

    fun buildRulesMap(rules: List<String>): Map<Int, Set<Int>> {
        return rules
            .map { it.split("|").map { it.toInt() } }
            .groupBy({ it[0] }, { it[1] })
            .mapValues { it.value.toSet() }
    }

    fun checkLine(rulesMap: Map<Int, Set<Int>>, numbers: List<Int>): Boolean {
        return numbers.withIndex().none { (i, number) ->
            rulesMap[number]?.intersect(numbers.slice(0..i))?.isNotEmpty() == true
        }
    }

    fun part1(rules: List<String>, toCheck: List<String>): Int {
        val rulesMap = buildRulesMap(rules)
        return toCheck.sumOf {
            val numbers = it.split(",").map { it.toInt() }
            if (checkLine(rulesMap, numbers)) numbers[numbers.size / 2] else 0
        }
    }


    fun part2(rules: List<String>, toCheck: List<String>): Int {
        val rulesMap = buildRulesMap(rules)
        var ans = 0
        for (line in toCheck) {
            var numbers = line.split(",").map { it.toInt() }.toMutableList()
            if (checkLine(rulesMap, numbers)) continue

            while (!checkLine(rulesMap, numbers)) {
                for (i in numbers.indices) {
                    val intersect = rulesMap[numbers[i]]?.intersect(numbers.slice(0..i))
                    if (intersect?.isNotEmpty() == true) {
                        val toSwap = numbers.indexOf(intersect.first())
                        numbers[i] = numbers[toSwap].also { numbers[toSwap] = numbers[i] }
                        break
                    }
                }
            }
            ans += numbers[numbers.size / 2]
        }
        return ans

    }

    val (rules1, toCheck1) = readInput("testInput").split("\n\n").map { it.lines() }
    check(part1(rules1, toCheck1) == 143) { "wrong part 1! returned ${part1(rules1, toCheck1)}" }
    val (rules2, toCheck2) = readInput("testInput2").split("\n\n").map { it.lines() }
    check(part2(rules2, toCheck2) == 123) { "wrong part 2! returned ${part2(rules2, toCheck2)}" }
    val (rules, toCheck) = readInput("input").split("\n\n").map { it.lines() }
    part1(rules, toCheck).println()
    part2(rules, toCheck).println()
}