package advent.twoK19

val day6SampleInput1 = """
COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L
""".trimIndent()

val day6SampleInput2 = """
COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L
K)YOU
I)SAN
""".trimIndent()

val day6Input = fileToArr("src/main/resources/day_6.txt")

fun main() {
    val relations = mutableMapOf<String, List<String>>()

    var orbitsSize = day6Part1(relations)
    println(orbitsSize)
}

private fun day6Part1(relations: MutableMap<String, List<String>>): Int {
    day6Input.forEach { str ->
        val splitted = str.split(")")
        if (relations[splitted.last()] == null) {
            relations[splitted.last()] = listOf(splitted.first())
        } else {
            relations[splitted.last()] = relations[splitted.last()]!!.plus(splitted.first())
        }
    }

    // println(relations)
    var orbitsSize = 0

    fun findOrbits(planet: String) {
        // println(planet)
        orbitsSize += relations[planet]?.size ?: 0
        relations[planet]?.forEach { findOrbits(it) }
    }

    relations.forEach { planet, orbits ->
        if (orbits.isNotEmpty()) {
            findOrbits(planet)
        }
    }
    return orbitsSize
}

