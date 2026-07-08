package t2_extensions

fun <T> List<T>.second(): T? {
    if (size <= 1) throw NoSuchElementException("List has no second element")

    return this[1]
}