import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/$name.txt").readText().trim()

fun readLines(name: String) = readInput(name).lines()

fun Any?.println() = println(this)


val directions8 = listOf(
    Point(-1, 0),  // up
    Point(-1, 1),  // up-right
    Point(0, 1),   // right
    Point(1, 1),   // down-right
    Point(1, 0),   // down
    Point(1, -1),  // down-left
    Point(0, -1),  // left
    Point(-1, -1)  // up-left
)

val directions4 = listOf(
    Point(-1, 0),  // up
    Point(0, 1),   // right
    Point(1, 0),   // down
    Point(0, -1),  // left
)

val UP = directions4[0]
val RIGHT = directions4[1]
val DOWN = directions4[2]
val LEFT = directions4[3]

data class Point(val i: Int, val j: Int) {
    fun isWithinBounds(input: List<String>): Boolean {
        return i in input.indices && j in input[0].indices
    }

    operator fun plus(dir: Point): Point = Point(i + dir.i, j + dir.j)
    operator fun minus(dir: Point): Point = Point(i - dir.i, j - dir.j)
}

operator fun List<String>.get(p: Point): Char = this[p.i][p.j]