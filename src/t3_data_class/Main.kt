package t3_data_class


// Задача: доменная модель заказа.
// data class Product(val id: String, val name: String, val price: Double)
// data class OrderItem(val product: Product, val quantity: Int)
// data class Order(val id: String, val items: List<OrderItem>, val status: OrderStatus)
// Функция fun Order.total(): Double — сумма по всем items
// Показать copy(): написать функцию fun Order.markAsPaid(): Order, которая возвращает новый Order с изменённым статусом, не трогая исходный
// Destructuring: data class Pair2(val first: Double, val second: Double), распаковать через val (a, b) = ...
// Проверить руками (или тестом), что equals у двух Order с одинаковыми полями возвращает true, а toString() печатает читаемо


//  @JvmInline value class ProductId(val value: String)
//  @JvmInline value class OrderId(val value: String)
//  Переписать Product и Order из 1.3, используя эти value classes вместо голых String
//  Написать функцию fun findProduct(id: ProductId, products: List<Product>): Product? и показать, что передать туда OrderId вместо ProductId не скомпилируется — это и есть весь смысл упражнения
//  Value class с валидацией в init {}: @JvmInline value class Percentage(val value: Int) { init { require(value in 0..100) } }

//  Сгенерируй список из 20-30 Order — можно вручную, можно циклом со случайными данными
//  groupBy — сгруппируй заказы по status, напечатай количество в каждой группе
//  Посчитай общую выручку по заказам со статусом PAID (filter + sumOf)
//  Топ-5 заказов по сумме (sortedByDescending + take)
//  partition — раздели на заказы дороже 100 и дешевле
//  associateBy — сделай Map<String, Order> по id, найди конкретный заказ по id за O(1)
//  Сравни eager vs asSequence() — оберни оба варианта в measureAndLog из 1.5 (если делал), запусти на списке из 10 000 элементов, сравни время
fun main() {
    val order = OrderFactory.create(5);

    val totalPrice = order.items.sumOf { it.product.price * it.quantity }
    println("Total price: $totalPrice")

    println("Order paid: ${order.isPaid}")
    val paidOrder = order.markAsPaid();
    println("Order paid: ${paidOrder.isPaid}")

    val (a, b, c) = order.items[0].product
    println("Product details: $a, $b, $c") // Desctruct declaration

    val secondOrder = OrderFactory.create(5);
    println("Orders are equal: ${order == secondOrder}") // Compare two orders

    println(secondOrder)

    val filter = order.findProduct(ProductId("1"))
    println("Found product: $filter")

    // ----------------------------------
    // groupBy — сгруппируй заказы по status, напечатай количество в каждой группе
    val orders = OrderFactory.createList(20)
    val groupedOrders = orders.groupBy { it.status }
    groupedOrders.map {
        println("Status: ${it.key}, Count: ${it.value.size}")
    }

    // ---
    // Посчитай общую выручку по заказам со статусом PAID (filter + sumOf)
    val totalRevenue = orders.filter { it.status.isPaid }.sumOf { it.total }
    println("Total revenue for PAID orders: $totalRevenue")

    // ---
    // Топ-5 заказов по сумме (sortedByDescending + take)
    val topOrders = orders.sortedByDescending { it.total }.take(5)

    // ---
    // partition — раздели на заказы дороже 100 и дешевле
    val (expensiveOrders, cheapOrders) = orders.partition { it.total > 100 }

    // ---
    // associateBy — сделай Map<String, Order> по id, найди конкретный
    val orderMap = orders.associateBy { it.id }
    val spec = orderMap[OrderId("1")]

    // ---
    // Сравни eager vs asSequence() — оберни оба варианта в measure
    val bigOrderList = OrderFactory.createList(100_000)
    // Time taken: 24 ms
    measureAndLog {
        val total = bigOrderList.filter { it.status.isPaid }.sumOf { it.total }
        println("Total revenue for PAID orders (eager): $total")
    }
    // Time taken: 9 ms
    measureAndLog {
        val total = bigOrderList.asSequence().filter { it.status.isPaid }.sumOf { it.total }
        println("Total revenue for PAID orders (lazy): $total")
    }
}

inline fun measureAndLog(block: () -> Unit) {
    val start = System.currentTimeMillis();
    block()
    val end = System.currentTimeMillis();
    println("Time taken: ${end - start} ms")
}