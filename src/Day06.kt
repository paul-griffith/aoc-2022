fun main() {
    fun findMarker(input: String, bufferSize: Int): Int {
        var buffer = input.slice(0..bufferSize)
        for ((i, char) in input.drop(bufferSize).withIndex()) {
            buffer += char
            buffer = buffer.slice(1..bufferSize)
            if (buffer.length == bufferSize && buffer.toSet().size == bufferSize) {
                return i + bufferSize + 1
            }
        }
        return -1
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

