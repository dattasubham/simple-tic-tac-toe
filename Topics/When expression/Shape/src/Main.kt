fun main(args: Array<String>) {
    val n = readln().toInt()
    var choose: String? = null
    when(n) {
        1 -> choose = "square"
        2 -> choose = "circle"
        3 -> choose = "triangle"
        4 -> choose = "rhombus"
    }
    if (choose === null) {
        println("There is no such shape!")
    } else {
        println("You have chosen a $choose")
    }
}