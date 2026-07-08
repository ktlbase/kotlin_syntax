package t3_data_class

@JvmInline
value class OrderId(override val value: String) : Id {
    init {
        Id.validate(value, this::class::simpleName.toString())
    }
}

data class Order(
    val id: OrderId,
    val items: List<OrderItem>,
    val status: OrderStatus = OrderStatus.PENDING,
) {
    /// Считаем общую стоимость заказа
    fun total(): Double = items.sumOf { it.product.price * it.quantity }

    /// Находим продукт по id в заказе
    fun findProduct(id: ProductId): Product? = items.map { it.product }.find { it.id == id }

    /// Создаем копию заказа с новым статусом
    fun markAs(status: OrderStatus): Order = this.copy(status = status)

    /// Создаем копию заказа со статусом оплаты
    fun markAsPaid(): Order = this.markAs(status = OrderStatus.PAID)


    /// Заказ оплачен?
    val isPaid: Boolean
        get() = status.isPaid
}