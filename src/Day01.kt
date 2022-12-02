fun caloriesByElf(input: Sequence<String>): List<Int> {
    return buildList {
        val currentElf = mutableListOf<Int>()
        for (line in input) {
            if (line.isBlank()) {
                add(currentElf.sum())
                currentElf.clear()
            } else {
                currentElf += line.toInt()
            }
        }
    }
}

fun part1(caloriesByElf: List<Int>): Int {
    return caloriesByElf.max()
}

fun part2(caloriesByElf: List<Int>): Int {
    return caloriesByElf.sortedDescending().take(3).sum()
}

fun main() {
    val caloriesByElf = caloriesByElf(readInput("day01"))

    println(part1(caloriesByElf))
    println(part2(caloriesByElf))
}
