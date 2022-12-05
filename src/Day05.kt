import java.lang.StringBuilder

fun main() {

    val cols = 9

    data class Movement(val items: Int, val from: Int, val to: Int)

    fun parse(line: String): List<Char?> {
        val res = mutableListOf<Char?>()
        for (i in line.indices step 4) {
            res.add(if (line[i + 1].isWhitespace()) null else line[i + 1])
        }
        return res
    }

    fun parseMovement(line: String): Movement {
        val sb = StringBuilder()
        val res = mutableListOf<Int>()
        var skip = true
        for (c in line) {
            if (c.isDigit()) {
                skip = false
                sb.append(c)
            } else if (!skip) {
                res.add(sb.toString().toInt())
                sb.setLength(0)
                skip = true
            }
        }
        res.add(sb.toString().toInt())
        return Movement(res[0], res[1], res[2])
    }

    fun solve(input: List<String>, multitake: Boolean) : String {
        val stacks = MutableList(cols) { mutableListOf<Char>() }
        var idx = 0
        while (true) {
            val line = input[idx++]
            if (line.startsWith(" 1")) break
            val parsed = parse(line)
            for (i in parsed.indices) {
                if (parsed[i] != null) {
                    stacks[i].add(parsed[i]!!)
                }
            }
        }
        idx++
        for (stack in stacks) stack.reverse()
        while (idx < input.size) {
            val move = parseMovement(input[idx])
            val items = stacks[move.from - 1].takeLast(move.items)
            stacks[move.from - 1] = stacks[move.from - 1].dropLast(move.items).toMutableList()
            val toAdd = if (multitake) items else items.reversed()
            stacks[move.to - 1].addAll(toAdd)
            idx++
        }
        val sb = StringBuilder()
        for (stack in stacks) sb.append(stack.last())
        return sb.toString()
    }


    fun part1(input: List<String>): String {
        return solve(input, false)
    }

    fun part2(input: List<String>): String {
        return solve(input, true)
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
