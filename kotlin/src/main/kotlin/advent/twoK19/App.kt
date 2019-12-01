package advent.twoK19

class App {

    companion object {
        private const val greeting = "Hello Earth!"
    }

    val greeting: String
        get() {
            return App.greeting
        }
}

fun main(args: Array<String>) {
    println(App().greeting)
}
