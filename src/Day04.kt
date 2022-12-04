import kotlin.math.max
import kotlin.math.min

fun main() {

    data class Segment(val start: Int, val end: Int)

    fun parse(line: String): Pair<Segment, Segment> {
        val (fst, snd) = line.split(',')
        val (fst1, fst2) = fst.split('-').map { x -> x.toInt() }
        val (snd1, snd2) = snd.split('-').map { x -> x.toInt() }
        return Pair(Segment(fst1, fst2), Segment(snd1, snd2))
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        for (line in input) {
            val (fst, snd) = parse(line)
            if (fst.start == fst.end ) {
                ans += if (fst.start >= snd.start && fst.start <= snd.end) 1 else 0
            } else if (snd.start == snd.end) {
                ans += if (snd.start >= fst.start && snd.start <= fst.end) 1 else 0
            } else {
                val intersection = max(min(fst.end, snd.end) - max(fst.start, snd.start), 0)
                if (intersection == (fst.end - fst.start) || intersection == (snd.end - snd.start)) {
                    ans++
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        for (line in input) {
            val (fst, snd) = parse(line)
            ans += if (fst.start == fst.end) {
                if (fst.start >= snd.start && fst.start <= snd.end) 1 else 0
            } else if (snd.start == snd.end) {
                if (snd.start >= fst.start && snd.start <= fst.end) 1 else 0
            } else {
                val intersection = max(min(fst.end, snd.end) - max(fst.start, snd.start), 0)
                if (intersection > 0 || fst.end == snd.start || snd.end == fst.start) 1 else 0
            }
        }
        return ans
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
