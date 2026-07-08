package t3_data_class

object ProductFactory {
    fun create(a: Int): Product {
        return Product(
            id = ProductId(a.toString()),
            name = "Product $a",
            price = (5..500).random().toDouble(),
        )
    }

    fun createList(count: Int): List<Product> {
        return (1..count).map { create(it) }
    }
}

object OrderItemFactory {
    fun create(a: Int): OrderItem {
        return OrderItem(
            product = ProductFactory.create(a),
            quantity = (1..10).random(),
        )
    }

    fun createList(count: Int): List<OrderItem> {
        return (1..count).map { create(it) }
    }
}

object OrderFactory {
    fun create(a: Int): Order {
        val items = (1..10).map { OrderItemFactory.create(a) }
        return Order(
            id = OrderId(a.toString()),
            items = items,
            status = OrderStatus.entries.random()
        )
    }

    fun createList(count: Int): List<Order> {
        return (1..count).map { create(it) }
    }
}