package t2_extensions

private val MAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

fun String.isValidMail(): Boolean = MAIL_REGEX.matches(this)
