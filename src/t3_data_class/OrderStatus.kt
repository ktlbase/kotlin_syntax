package t3_data_class

enum class OrderStatus {
    PENDING,
    PAID,
    SHIPPED,
    CANCELLED;

    val isPending: Boolean
        get() = this == PENDING

    val isPaid: Boolean
        get() = this == PAID

    val isShipped: Boolean
        get() = this == SHIPPED

    val isCancelled: Boolean
        get() = this == CANCELLED
}
