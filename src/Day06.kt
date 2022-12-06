fun main() {

    fun solve(input: String, msgLen: Int): Int {
        val buf = mutableMapOf<Char, Int>()
        for (i in 0 until msgLen) buf[input[i]] = buf.getOrDefault(input[i], 0) + 1
        if (buf.size == msgLen) {
            return msgLen
        }
        for (i in msgLen until input.length) {
            buf[input[i - msgLen]] = buf[input[i - msgLen]]!! - 1
            buf[input[i]] = buf.getOrDefault(input[i], 0) + 1
            if (buf[input[i - msgLen]]!! == 0) {
                buf.remove(input[i - msgLen])
            }
            if (buf.size == msgLen) {
                return i + 1
            }
        }
        return -1
    }

    fun part1(input: String): Int {
        return solve(input, 4)
    }

    fun part2(input: String): Int {
        return solve(input, 14)
    }

    val input = readInput("Day06").first()
    println(part1(input))
    println(part2(input))
}
