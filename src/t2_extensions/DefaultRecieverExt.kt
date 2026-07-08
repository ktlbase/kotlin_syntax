package t2_extensions

fun String?.withDefault(default: String): String {
    if (this == null) return default;
    return this;
}