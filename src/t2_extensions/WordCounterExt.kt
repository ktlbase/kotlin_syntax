package t2_extensions

val String.wordCount: Int
    get() = this.split(" ").size