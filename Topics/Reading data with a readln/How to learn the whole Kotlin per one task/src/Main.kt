fun main() {
    val arr: Array<String> = Array(5) { "" }
    for (i in 0 until 5) {
        arr[i] = readln().trim()
    }
    println(arr.joinToString(separator = " "))
}