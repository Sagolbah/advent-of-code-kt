fun main() {

    fun buildTree(input: List<String>): Node {
        val root: Node.Dir = Node.Dir(mutableListOf(), null, "/")
        var curr = root
        for (line in input) {
            val arr = line.split(" ")
            if (arr.size == 3 && arr[1] == "cd") {
                curr = when (val dest = arr[2]) {
                    ".." -> {
                        curr.parent!!
                    }

                    "/" -> {
                        root
                    }

                    else -> {
                        curr.children.first { node -> node is Node.Dir && node.name == dest } as Node.Dir
                    }
                }
            } else if (arr[0] != "$") {  // skip cd
                val toAdd = when (arr[0]) {
                    "dir" -> Node.Dir(mutableListOf(), curr, arr[1])
                    else -> Node.File(arr[0].toInt(), arr[1])
                }
                curr.children.add(toAdd)
            }
        }
        return root
    }

    fun dfs(node: Node, ans: MutableList<Int>): Int {
        return when (node) {
            is Node.File -> node.size
            is Node.Dir -> {
                var sum = 0
                for (child in node.children) {
                    sum += dfs(child, ans)
                }
                ans.add(sum)
                sum
            }
        }
    }

    fun part1(input: List<String>): Int {
        val root = buildTree(input)
        val ans = mutableListOf<Int>()
        dfs(root, ans)
        return ans.filter { x -> x <= 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val root = buildTree(input)
        val ans = mutableListOf<Int>()
        dfs(root, ans)
        val available = 70000000 - ans.max()  // it will be root
        val need = 30000000 - available
        return ans.filter { x -> x >= need }.min()
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

sealed class Node {
    data class Dir(val children: MutableList<Node>, val parent: Dir?, val name: String) : Node()

    data class File(val size: Int, val name: String) : Node()
}