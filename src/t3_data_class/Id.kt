package t3_data_class

interface Id {
    val value: String

    companion object {
        fun validate(value: String, name: String) =
            require(value.isNotBlank()) { "$name cannot be blank" }
    }
}