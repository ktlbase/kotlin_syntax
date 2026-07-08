package t4_inline_reified

// Потрогать inline
inline fun repeatAction(times: Int = 5, action: (v: Int) -> Unit) {
    for (i in 1..times) {
        action(i)
    }
}


fun main() {
    repeatAction(action = { i -> print(i) })
}