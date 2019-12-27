package advent.twoK19

private val input1 = """
.#..#
.....
#####
....#
...##
"""

private val input2 = """
...###.#########.####
.######.###.###.##...
####.########.#####.#
########.####.##.###.
####..#.####.#.#.##..
#.################.##
..######.##.##.#####.
#.####.#####.###.#.##
#####.#########.#####
#####.##..##..#.#####
##.######....########
.#######.#.#########.
.#.##.#.#.#.##.###.##
######...####.#.#.###
###############.#.###
#.#####.##..###.##.#.
##..##..###.#.#######
#..#..########.#.##..
#.#.######.##.##...##
.#.##.#####.#..#####.
#.#.##########..#.##.
""".trimIndent()

private data class SpaceObject(val type: String, val x: Int, val y: Int, val visibleObjects: List<SpaceObject>)

fun main() {
    val allAsteroids = parseInput(input2)
    // println(allAsteroids)

    val allAsteroidsWithVis = allAsteroids.map { ast ->
        val visibleObjects = findVisibleObjects(ast, allAsteroids.filter { it != ast })
        SpaceObject(ast.type, ast.x, ast.y, visibleObjects)
    }

    val bestOption = allAsteroidsWithVis.maxBy { it.visibleObjects.size }
    println(bestOption)
    println(bestOption!!.visibleObjects.size)
}

private fun parseInput(input: String): List<SpaceObject> {
    val allData = input
            .split("\n")
            .filter { it.isNotBlank() }
            .map { it.split("").filter { data -> data.isNotBlank() } }
    // println(allData)
    val allAsteroids = allData.mapIndexed { y, layer ->
        layer.mapIndexed { x, s -> SpaceObject(s, x, y, emptyList()) }
    }.flatten().filter { it.type != "." }
    // println(allAsteroids)
    return allAsteroids
}

private fun findVisibleObjects(ast: SpaceObject, otherAst: List<SpaceObject>): List<SpaceObject> {
    return otherAst.filter { obj ->
        isVisible(ast, obj, otherAst.filter { it != obj })
    }
}

private fun isVisible(obj1: SpaceObject, obj2: SpaceObject, others: List<SpaceObject>): Boolean {
    return others
            // if you are outside the range of obj1 & obj2 you do not matter
            .filter { obj3 ->
                val r1 = if (obj1.x > obj2.x) (obj2.x..obj1.x) else (obj1.x..obj2.x)
                val r2 = if (obj1.y > obj2.y) (obj2.y..obj1.y) else (obj1.y..obj2.y)
                r1.contains(obj3.x) && r2.contains(obj3.y)
            }
            .none { obj3 ->
                (obj1.x * (obj2.y - obj3.y)) + (obj2.x * (obj3.y - obj1.y)) + (obj3.x * (obj1.y - obj2.y)) == 0
            }
}