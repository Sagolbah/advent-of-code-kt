fun main() {
    fun outcome(opp: Char, my: Char): Int {
        return when (opp) {
            'A' -> when (my) {
                'X' -> 3
                'Y' -> 6
                'Z' -> 0
                else -> error("unknown choice")
            }

            'B' -> when (my) {
                'X' -> 0
                'Y' -> 3
                'Z' -> 6
                else -> error("unknown choice")
            }

            'C' -> when (my) {
                'X' -> 6
                'Y' -> 0
                'Z' -> 3
                else -> error("unknown choice")
            }

            else -> error("unknown choice")
        }
    }

    // too lazy to refactor :(
    fun getTurn(opp: Char, req: Char): Char {
        return when (opp) {
            // rock
            'A' -> when (req) {
                'X' -> 'Z'
                'Y' -> 'X'
                'Z' -> 'Y'
                else -> error("unknown choice")
            }
            // paper
            'B' -> when (req) {
                'X' -> 'X'
                'Y' -> 'Y'
                'Z' -> 'Z'
                else -> error("unknown choice")
            }
            // scissors
            'C' -> when (req) {
                'X' -> 'Y'
                'Y' -> 'Z'
                'Z' -> 'X'
                else -> error("unknown choice")
            }

            else -> error("unknown choice")
        }
    }

    fun part1(input: List<String>): Int {
        var score = 0
        for (line in input) {
            val opponent = line[0]
            val my = line[2]
            score += (my - 'X') + 1 + outcome(opponent, my)
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        for (line in input) {
            val opponent = line[0]
            val req = line[2]
            val my = getTurn(opponent, req)
            score += (my - 'X') + 1 + outcome(opponent, my)
        }
        return score
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
