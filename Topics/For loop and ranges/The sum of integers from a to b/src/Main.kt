fun main() {
    // put your code here
    val a = readln().toInt()
    val b = readln().toInt()
    var sum: Int = 0

    for (x in a..b) {
        sum += x
    }

    println(sum)
}