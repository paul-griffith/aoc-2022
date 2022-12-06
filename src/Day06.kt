fun main() {
    fun findMarker(input: String, bufferSize: Int): Int {
        return input.windowedSequence(bufferSize).indexOfFirst {
            it.toSet().size == bufferSize
        } + bufferSize
    }

    fun part1(lines: Sequence<String>): Int {
        return findMarker(lines.first(), 4)
    }

    fun part2(lines: Sequence<String>): Int {
        return findMarker(lines.first(), 14)
    }

//    println(part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
//    println(part1("bvwbjplbgvbhsrlpgdmjqwftvncz"))
//    println(part1("nppdvjthqldpwncqszvftbrmjlhg"))
//    println(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") )
//    println(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") )
    println(part1(readInput("day06")))
    println(part2(readInput("day06")))

//    println(part1(realStack, readInput("day05")))
}

