package advent.twoK19

data class Pixel(val x: Int, val y: Int, val layer: Int, val value: Int?)

fun main() {
    val inputs = fileToArr("src/main/resources/day_8.txt")
            .first()
            .split("")
            .filter { it.isNotBlank() }

    var layer = 1
    val layers = mutableMapOf(layer to emptyList<Pixel>())
    var layerContents = emptyList<Pixel>()

    var x = 0
    var y = 0

    inputs.forEach { numStr ->
        if (layerContents.size % 25 == 0) {
            y += 1
            x = 0
        }
        if (layerContents.size == 150) {
            layers[layer] = layerContents
            layerContents = emptyList()
            layer += 1

            x = 0
            y = 1
        }
        x += 1
        layerContents = layerContents.plus(Pixel(x, y, layer, Integer.parseInt(numStr)))
    }
    layers[layer] = layerContents

    // println(layers)
    println(part1(layers))
    part2(layers)
}

fun part1(layers: Map<Int, List<Pixel>>): Int {
    var minZeroesLayer = Pair(0, Int.MAX_VALUE)
    layers.keys.forEach { key ->
        val zeroCount = layers[key]!!.count { it.value == 0 }
        if (zeroCount < minZeroesLayer.second) {
            minZeroesLayer = Pair(key, zeroCount)
        }
    }
    val lyr = layers[minZeroesLayer.first]!!
    return lyr.count { it.value == 1 } * lyr.count { it.value == 2 }
}

fun part2(layers: Map<Int, List<Pixel>>) {
    val sampleLayer = layers.keys.first()
    val emptyLayer = layers[sampleLayer]!!.map { entry ->
        Pixel(entry.x, entry.y, 99999, null)
    }

    val allPixels = layers.values.flatten()
    val finalLayer = emptyLayer.map x@{ pixel ->
        val matchingPixels = allPixels.filter { it.x == pixel.x && it.y == pixel.y }
        val finalPixel = matchingPixels.find { it.value != 2 } ?: Pixel(0, 0, 0, 2)
        return@x Pixel(pixel.x, pixel.y, pixel.layer, finalPixel.value)
    }
    finalLayer.forEachIndexed { idx, pixel ->
        if (pixel.value != 0) print(pixel.value) else print(" ")
        if (pixel.y < 6 && finalLayer[idx + 1].y > finalLayer[idx].y) {
            println("")
        }
    }
}