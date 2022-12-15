// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    while(true) {
        val something = readln()

        val d = something.toDouble()
        val f = d.toFloat()
        val i = f.toInt()
        val b = i.toByte()

        println(d)
        println(f)
        println(i)
        println(b)
        println(something.toBoolean())
    }
}
