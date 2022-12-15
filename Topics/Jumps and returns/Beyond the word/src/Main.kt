fun main() {
    // put your code here
    val str = readln()
    for (ch in 'a'..'z') {
        if (str.contains(ch)) continue
        print(ch)
    }
}