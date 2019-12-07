package advent.twoK19

//val day6SampleInput1 = """
//COM)B
//B)C
//C)D
//D)E
//E)F
//B)G
//G)H
//D)I
//E)J
//J)K
//K)L
//""".trimIndent()
//
//val day6SampleInput2 = """
//COM)B
//B)C
//C)D
//D)E
//E)F
//B)G
//G)H
//D)I
//E)J
//J)K
//K)L
//K)YOU
//I)SAN
//""".trimIndent()

val day6Input = fileToArr("src/main/resources/day_6.txt")
// val day6Input = day6SampleInput2.split("\n")

fun main() {
    println(day6Part1())
    println(day6Part2())
}

private fun day6Part1(): Int {
    var orbitsSize = 0
    val relations = mutableMapOf<String, List<String>>()

    day6Input.forEach { str ->
        val splitted = str.split(")")
        if (relations[splitted.last()] == null) {
            relations[splitted.last()] = listOf(splitted.first())
        } else {
            relations[splitted.last()] = relations[splitted.last()]!!.plus(splitted.first())
        }
    }

    fun findOrbits(planet: String) {
        orbitsSize += relations[planet]?.size ?: 0
        relations[planet]?.forEach { findOrbits(it) }
    }

    relations.forEach { (planet, orbits) ->
        if (orbits.isNotEmpty()) {
            findOrbits(planet)
        }
    }
    return orbitsSize
}

private fun day6Part2(): Int {
    val relations = mutableMapOf<String, List<String>>()

    day6Input.forEach { str ->
        val splitted = str.split(")")
        if (relations[splitted.first()] == null) {
            relations[splitted.first()] = listOf(splitted.last())
        } else {
            relations[splitted.first()] = relations[splitted.first()]!!.plus(splitted.last())
        }
    }

    fun findParent(planet: String): String {
        return relations.keys.find { relations[it]!!.contains(planet) }!!
    }

    fun findAllChildren(planet: String): List<String> {
        var agg = emptyList<String>()

        tailrec fun getChildren(planet: String) {
            val children = relations[planet] ?: emptyList()
            agg = agg.plus(children)

            if(children.isNotEmpty()) {
                children.forEach { getChildren(it) }
            }
        }

        getChildren(planet)
        return agg
    }

    var up = 0
    var down = 0

    tailrec fun goUpFromYou(myParent: String): String {
        val allChildren = findAllChildren(myParent)
        return if (allChildren.contains("SAN")) {
            myParent
        } else {
            up += 1
            goUpFromYou(findParent(myParent))
        }
    }

    val parentHoldingSanta = goUpFromYou(findParent("YOU"))

    tailrec fun keepFinding(some: String) {
        val par = findParent(some)
        if (par != parentHoldingSanta) {
            down += 1
            keepFinding(par)
        }
    }

    keepFinding("SAN")
    return up + down
}

