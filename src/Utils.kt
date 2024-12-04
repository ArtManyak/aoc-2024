import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
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