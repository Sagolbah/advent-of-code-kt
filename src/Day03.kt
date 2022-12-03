fun main() {
    fun getPriority(symbol: Char) : Int {
        return if (symbol in 'a'..'z') {
            symbol - 'a' + 1
        } else {
            symbol - 'A' + 27
        }
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        for (line in input) {
            val fst = line.substring(0, line.length / 2).toSet()
            val snd = line.substring(line.length / 2).toSet()
            val symbol = fst.intersect(snd).first()
            ans += getPriority(symbol)
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        for (i in input.indices step 3) {
            val symbol = input[i].toSet().intersect(input[i+1].toSet()).intersect(input[i+2].toSet()).first()
            ans += getPriority(symbol)
        }
        return ans
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
