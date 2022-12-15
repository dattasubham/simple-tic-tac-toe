fun main() {
    // put your code here
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()
    val d = readln().toInt()

    for (x in 0..1000) {
        if (evaluate(a, b, c, d, x) == 0) {
            println(x)
        }
    }
}

fun evaluate(a: Int, b: Int, c: Int, d: Int, x: Int): Int {
    return a * x * x * x + b * x * x + c * x + d
}