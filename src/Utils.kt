import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/$name.txt").readText().trim()

fun readLines(name: String) = readInput(name).lines()

fun Any?.println() = println(this)


val directions8 = listOf(
    Pair(-1, 0),  // up
    Pair(-1, 1),  // up-right
    Pair(0, 1),   // right
    Pair(1, 1),   // down-right
    Pair(1, 0),   // down
    Pair(1, -1),  // down-left
    Pair(0, -1),  // left
    Pair(-1, -1)  // up-left
)

val directions4 = listOf(
    Pair(-1, 0),  // up
    Pair(0, 1),   // right
    Pair(1, 0),   // down
    Pair(0, -1),  // left
)

data class Point(val i: Int, val j: Int) {
    fun isWithinBounds(input: List<String>): Boolean {
        return i in input.indices && j in input[0].indices
    }
}