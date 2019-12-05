package advent.twoK19

val inputDay2 = listOf(1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,9,23,1,23,6,27,1,9,27,31,1,31,10,35,2,13,35,39,1,39,10,43,1,43,9,47,1,47,13,51,1,51,13,55,2,55,6,59,1,59,5,63,2,10,63,67,1,67,9,71,1,71,13,75,1,6,75,79,1,10,79,83,2,9,83,87,1,87,5,91,2,91,9,95,1,6,95,99,1,99,5,103,2,103,10,107,1,107,6,111,2,9,111,115,2,9,115,119,2,13,119,123,1,123,9,127,1,5,127,131,1,131,2,135,1,135,6,0,99,2,0,14,0)

fun main() {
    // Part 1
    println(calc(12, 2, inputDay2))

    // Part 2
    for (i in 0..99) {
        for (j in 0..99) {
            if (calc(i, j, inputDay2) == 19690720) {
                println(100 * i + j)
                break
            }
        }
    }
}

private fun calc(noun: Int, verb: Int, input: List<Int>): Int {
    val mutableInput = input.toMutableList()
    mutableInput[1] = noun
    mutableInput[2] = verb

    var i = 0
    val grouped = groupByFour(mutableInput)

    while(true) {
        val group = grouped[i]

        if(group[0] == 1) {
            mutableInput[group[3]] = mutableInput[group[1]] + mutableInput[group[2]]
        }
        if(group[0] == 2) {
            mutableInput[group[3]] = mutableInput[group[1]] * mutableInput[group[2]]
        }
        if(group[0] == 99) {
            break
        }
        i += 1
    }

    return mutableInput[0]
}

private fun groupByFour(input: List<Int>): List<List<Int>> {
    var grouped = emptyList<List<Int>>()
    var group = emptyList<Int>()

    input.forEachIndexed { idx, elem ->
        group = group.plus(elem)
        if ((idx + 1) % 4 == 0) {
            grouped = grouped.plus<List<Int>>(group)
            group = emptyList()
        }
    }
    return grouped.plus<List<Int>>(group)
}