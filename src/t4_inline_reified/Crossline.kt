package t4_inline_reified

fun printThread() {
    println("Выполняется в потоке: ${Thread.currentThread().name}")
}

inline fun backgroundTask(crossinline work: () -> Unit) {
    Thread {
        work()
    }.start()
}

fun main() {
    backgroundTask {
        printThread()
    }

    printThread()
}