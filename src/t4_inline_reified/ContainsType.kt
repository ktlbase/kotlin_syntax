package t4_inline_reified

inline fun <reified T> List<*>.containsType(): Boolean = any { it is T }

fun main() {
    val list = listOf(1, "Hello", 3.14, true)

    println(list.containsType<Int>()) // Output: true
    println(list.containsType<String>()) // Output: true
    println(list.containsType<Double>()) // Output: true
    println(list.containsType<Boolean>()) // Output: true
    println(list.containsType<Char>()) // Output: false
}