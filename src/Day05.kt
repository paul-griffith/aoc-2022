private typealias SupplyStack = List<ArrayDeque<Char>>

fun main() {
    fun SupplyStack.debug() {
        val maxStack = maxOf { it.size }
        for (i in maxStack downTo 0) {
            forEach { column ->
                val value = column.getOrNull(i)
                if (value != null) {
                    print("[$value]")
                } else {
                    print("   ")
                }
            }
            println()
        }
        indices.forEach {
            print(" ${it + 1} ")
        }
        println()
    }

    fun part1(stack: SupplyStack, moves: Sequence<String>): String {
//        stack.debug()
//        println("-".repeat(20))
        val movePattern = """(\d+).+(\d+).+(\d+)""".toRegex()
        for (line in moves) {
//            println(moves)
            val (qty, from, to) = movePattern.find(line)!!.groupValues.drop(1).map(String::toInt)
            repeat(qty) {
                val popped = stack[from - 1].removeLast()
                stack[to - 1].addLast(popped)
//                stack.debug()
            }
//            println("-".repeat(20))
        }
//        stack.debug()
        return stack.map { it.last() }.toCharArray().concatToString()
    }

    fun part2(stack: SupplyStack, moves: Sequence<String>): String {
//        stack.debug()
//        println("-".repeat(20))
        val movePattern = """(\d+).+(\d+).+(\d+)""".toRegex()
        for (line in moves) {
//            println(moves)
            val (qty, from, to) = movePattern.find(line)!!.groupValues.drop(1).map(String::toInt)
            val onHook = List(qty) { stack[from - 1].removeLast() }.asReversed()
            stack[to - 1].addAll(onHook)
//            stack.debug()
//            println("-".repeat(20))
        }
//        stack.debug()
        return stack.map { it.last() }.toCharArray().concatToString()
    }

    fun ArrayDeque(chars: CharSequence): ArrayDeque<Char> {
        return ArrayDeque(chars.toList())
    }

    val sampleStack: SupplyStack = listOf(
        ArrayDeque("ZN"),
        ArrayDeque("MCD"),
        ArrayDeque("P"),
    )

    val sampleMoves = """
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent().lineSequence()

    val realStack: SupplyStack = listOf(
        ArrayDeque("FCPGQR"),
        ArrayDeque("WTCP"),
        ArrayDeque("BHPMC"),
        ArrayDeque("LTQSMPR"),
        ArrayDeque("PHJZVGN"),
        ArrayDeque("DPJ"),
        ArrayDeque("LGPZFJTR"),
        ArrayDeque("NLHCFPTJ"),
        ArrayDeque("GVZQHTCW"),
    )

//    println(part1(realStack, readInput("day05")))
    println(part2(realStack, readInput("day05")))
}

