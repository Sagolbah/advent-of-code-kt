import kotlin.math.max

fun main() {
    fun solve1(input: List<String>): Long {
        var ans: Long = -1
        var curr: Long = 0
        for (line in input) {
            if (line.isEmpty()) {
                ans = max(ans, curr)
                curr = 0
            } else {
                curr += line.toLong()
            }
        }
        ans = max(ans, curr)
        return ans
    }

    fun solve2(input: List<String>): Long {
        val elves = mutableListOf<Long>()
        var curr: Long = 0  // assume all input is > 0
        for (line in input) {
            if (line.isEmpty()) {
                elves.add(curr)
                curr = 0
            } else {
                curr += line.toLong()
            }
        }
        if (curr != 0L) {
            elves.add(curr)
        }
        return elves.sortedDescending().take(3).sum()
    }

    val input = readInput("Day01")
    println(solve1(input))
    println(solve2(input))
}
