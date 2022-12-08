import kotlin.math.max
import kotlin.streams.toList

fun main() {

    fun processInput(input: List<String>): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        for (line in input) {
            res.add(line.codePoints().map { x -> x - '0'.code }.toList())
        }
        return res
    }

    fun part1(input: List<List<Int>>): Int {
        val ans = mutableSetOf<Pair<Int, Int>>()
        var mx = -1
        // left traversal
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] > mx) {
                    ans.add(Pair(i, j))
                    mx = input[i][j]
                }
            }
            mx = -1
        }
        // right traversal
        for (i in input.indices) {
            for (j in input[i].indices.reversed()) {
                if (input[i][j] > mx) {
                    ans.add(Pair(i, j))
                    mx = input[i][j]
                }
            }
            mx = -1
        }
        // upper traversal
        for (j in input.indices) {
            for (i in input.indices) {
                if (input[i][j] > mx) {
                    ans.add(Pair(i, j))
                    mx = input[i][j]
                }
            }
            mx = -1
        }
        // lower traversal
        for (j in input.indices) {
            for (i in input.indices.reversed()) {
                if (input[i][j] > mx) {
                    ans.add(Pair(i, j))
                    mx = input[i][j]
                }
            }
            mx = -1
        }
        return ans.size
    }

    fun part2(input: List<List<Int>>): Int {
        // I'll use here naive O(n^3) solution, but it can be solved with O(n^2) preprocessing
        var ans = -1
        for (i in 1..input.size - 2) {
            for (j in 1..input.size - 2) {
                var up = 0
                var down = 0
                var left = 0
                var right = 0
                val my = input[i][j]
                // up
                for (k in (0 until i).reversed()) {
                    up++
                    if (input[k][j] >= my) break
                }
                // down
                for (k in i + 1 until input.size) {
                    down++
                    if (input[k][j] >= my) break
                }
                // left
                for (k in (0 until j).reversed()) {
                    left++
                    if (input[i][k] >= my) break
                }
                // right
                for (k in j + 1 until input.size) {
                    right++
                    if (input[i][k] >= my) break
                }
                ans = max(ans, up * down * left * right)
            }
        }
        return ans
    }

    val input = processInput(readInput("Day08"))
    println(part1(input))
    println(part2(input))
}
