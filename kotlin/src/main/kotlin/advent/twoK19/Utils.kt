package advent.twoK19

import java.io.File

fun fileToArr(filePath: String): List<String> {
    return File(filePath).readLines()
}