private fun String.splitInHalf(): Pair<String, String> {
    return Pair(this.substring(0, length / 2), this.substring(length / 2))
}

private val Char.score: Int
    get() {
        val base = this.lowercase().first().code - 96
        return if (isUpperCase()) {
            base + 26
        } else {
            base
        }
    }

fun main() {
    fun part1(input: Sequence<String>): Int {
        return input.sumOf { line ->
            val (first, second) = line.splitInHalf()
//            println("$first $second")
            val intersection = first.toSet().intersect(second.toSet()).first()
//            println(intersection)
            val result = intersection.score
//            println(result)
            result
        }
    }

    fun part2(input: Sequence<String>): Int {
        return input.chunked(3).sumOf { group ->
//            println(group)
            val common = group.map(String::toSet).reduce { acc, next ->
                acc intersect next
            }.first()
//            println(common)
            common.score
        }
    }

    // val sample = sequenceOf(
    //     "vJrwpWtwJgWrhcsFMMfFFhFp",
    //     "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
    //     "PmmdzqPrVvPwwTWBwg",
    //     "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
    //     "ttgJtRGJQctTZtZT",
    //     "CrZsJsPPZsGzwwsLwLmpwMDw",
    // )

    println(part1(readInput("day03")))
    println(part2(readInput("day03")))
}
