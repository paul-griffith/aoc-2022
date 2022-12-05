fun main() {
    fun String.toRange(): IntRange {
        val (first, last) = split('-')
        return first.toInt()..last.toInt()
    }

    operator fun IntRange.contains(other: IntRange): Boolean {
        return other.first >= this.first && other.last <= this.last
    }

    fun part1(input: Sequence<String>): Int {
        // split to pairs
        // pair of strings to ranges
        // either range contains the other
        // count
        return input.count { line ->
            val (pair1, pair2) = line.split(',')
            val range1 = pair1.toRange()
            val range2 = pair2.toRange()
            range1 in range2 || range2 in range1
        }
    }

    fun part2(input: Sequence<String>): Int {
        return input.count { line ->
            val (pair1, pair2) = line.split(',')
            val range1 = pair1.toRange()
            val range2 = pair2.toRange()
            range1.intersect(range2).isNotEmpty()
        }
    }

    val sample = sequenceOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )

    println(part1(readInput("day04")))
    println(part2(readInput("day04")))
}

