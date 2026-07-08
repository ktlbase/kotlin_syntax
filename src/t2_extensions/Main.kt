package t2_extensions

/// Реализовать набор extension-функций.

fun main(){
    val mails = listOf("test@example.com", "invalid-mail", "user.name+tag@mail.co")
    for (mail in mails) {
        println("$mail valid = ${mail.isValidMail()}")
    }

    val numbers = listOf(10, 20, 30)
    println("second = ${numbers.second()}")

    val single = listOf(1)

    try {
        single.second()
    } catch (e: NoSuchElementException) {
        println("second on single-element list: ${e.message}")
    }
}