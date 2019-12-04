package advent.twoK19

fun main() {
    val valid = filterRange(387638, 919123)
    // println(valid)
    println(valid.size)
}

fun filterRange(from: Int, to: Int): List<Int> {
    val range = from..to
    return range.filter { hasDoubles2(it.toString()) && onlyIncrements(it.toString()) }
}

fun hasDoubles1(str: String): Boolean {
    var lastOne = ""
    var bool = false
    str.split("").filter { !it.isBlank() }.forEach { char ->
        if (char == lastOne) {
            bool = true
        }
        lastOne = char
    }
    return bool
}

fun hasDoubles2(str: String): Boolean {
    var lastOne = ""
    var groups = mutableMapOf<String, Int>()
    str.split("").filter { !it.isBlank() }.forEach { char ->
        if (char == lastOne) {
            groups[char] = if (groups[char] == null) 2 else (groups[char]!! + 1)
        }
        lastOne = char
    }
    return groups.values.contains(2)
}

fun onlyIncrements(str: String): Boolean {
    var lastOne = "3"
    var bool = true

    str.split("").filter { !it.isBlank() }.forEach { char ->
        if (Integer.parseInt(char) < Integer.parseInt(lastOne)) {
            bool = false
        }
        lastOne = char
    }
    return bool
}