private sealed interface Node {
    val name: String
    val size: Int
}

private data class File(
    override val name: String,
    override val size: Int
) : Node

private data class Directory(
    override val name: String,
    val children: MutableMap<String, Node> = mutableMapOf()
) : Node, MutableMap<String, Node> by children {
    lateinit var parent: Directory

    override val size: Int
        get() = children.values.sumOf { it.size }
}

private val pattern =
    """(\$ (?<cmd>cd|ls)(?> (?<target>.+))?|dir (?<directory>.+)|(?<filesize>\d+) (?<filename>.+))""".toRegex()

fun main() {
    fun parseCommandsToTree(lines: Sequence<String>): Directory {
        val root = Directory("/")
        var currentDirectory = root
        for (line in lines.drop(1)) {
            val matchResult = pattern.find(line)!!
            val cmd = matchResult.groups["cmd"]?.value
            val target = matchResult.groups["target"]?.value
            val directory = matchResult.groups["directory"]?.value
            val filesize = matchResult.groups["filesize"]?.value
            val filename = matchResult.groups["filename"]?.value
            if (cmd == "cd") {
                currentDirectory = if (target == "..") {
                    currentDirectory.parent
                } else {
                    val newCurrent = currentDirectory.getOrPut(target!!) { Directory(target) } as Directory
                    newCurrent.parent = currentDirectory
                    newCurrent
                }
            } else if (directory != null) {
                currentDirectory[directory] = Directory(directory)
            } else if (filename != null) {
                currentDirectory[filename] = File(filename, filesize!!.toInt())
            }
        }
        return root
    }

    fun Directory.depthFirstSearch(): Sequence<Node> {
        return sequence {
            yield(this@depthFirstSearch)
            for (child in children.values) {
                when (child) {
                    is Directory -> yieldAll(child.depthFirstSearch())
                    is File -> yield(child)
                }
            }
        }
    }

    fun part1(lines: Sequence<String>): Int {
        val root = parseCommandsToTree(lines)
        return root.depthFirstSearch().filterIsInstance<Directory>().filter { it.size < 100_000 }.sumOf { it.size }
    }

    fun part2(lines: Sequence<String>): Int {
        val root = parseCommandsToTree(lines)
        val currentFreeSpace = 70_000_000 - root.size
        val neededFreeSpace = 30_000_000
        val missingFreeSpace = neededFreeSpace - currentFreeSpace
        return root.depthFirstSearch()
            .filterIsInstance<Directory>()
            .filter { it.size >= missingFreeSpace }
            .minBy { it.size }
            .size
    }

    val sample = sequenceOf(
        "\$ cd /",
        "\$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "\$ cd a",
        "\$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "\$ cd e",
        "\$ ls",
        "584 i",
        "\$ cd ..",
        "\$ cd ..",
        "\$ cd d",
        "\$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k",
    )

//    println(part1(sample))
    println(part1(readInput("day07")))
    println(part2(readInput("day07")))
}

