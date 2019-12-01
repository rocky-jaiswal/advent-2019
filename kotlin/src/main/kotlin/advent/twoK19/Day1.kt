package advent.twoK19

import kotlin.math.floor

fun main() {
    val inputs = fileToArr("src/main/resources/day1_1.txt")

    val result1 = inputs.map { input ->
        part1(Integer.parseInt(input).toDouble())
    }.sum()

    val result2 = inputs.flatMap { input ->
        part2(Integer.parseInt(input).toDouble())
    }.sum()

    println(result1)
    println(result2)
}

fun part1(num: Double): Double {
    val data = num / 3.0
    return floor(data) - 2
}

fun part2(num: Double, res: List<Double> = emptyList<Double>()): List<Double> {
    val data = num / 3.0
    val rem = floor(data) - 2
    if (rem > 0) {
        return part2(rem, res.plus(rem))
    }
    return res
}