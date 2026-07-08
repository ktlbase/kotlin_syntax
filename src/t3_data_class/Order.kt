package t3_data_class

data class Order(
    val id: String,
    val items: List<OrderItem>,
    val status: OrderStatus = OrderStatus.PENDING,
) {
    /// Считаем общую стоимость заказа
    fun total(): Double = items.sumOf { it.product.price * it.quantity }

    /// Создаем копию заказа с новым статусом
    fun markAs(status: OrderStatus): Order = this.copy(status = status)

    /// Создаем копию заказа со статусом оплаты
    fun markAsPaid(): Order = this.markAs(status = OrderStatus.PAID)

    /// Заказ оплачен?
    val isPaid: Boolean
        get() = status.isPaid
}