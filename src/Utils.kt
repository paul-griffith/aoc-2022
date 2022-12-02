import kotlin.io.path.Path
import kotlin.io.path.bufferedReader

fun readInput(name: String) = Path("src", "$name.txt").bufferedReader().lineSequence()
