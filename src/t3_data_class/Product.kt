package t3_data_class

@JvmInline
value class ProductId(override val value: String) : Id {
    init {
        Id.validate(value, this::class::simpleName.toString())
    }
}

data class Product(
    val id: ProductId,
    val name: String,
    val price: Double,
)