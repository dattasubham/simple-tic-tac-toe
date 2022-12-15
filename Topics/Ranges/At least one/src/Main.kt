fun main() {
    val i1 = readln().toInt()
    val i2 = readln().toInt()
    val j1 = readln().toInt()
    val j2 = readln().toInt()
    val n = readln().toInt()

    println(n in i1..i2 || n in j1..j2)
}