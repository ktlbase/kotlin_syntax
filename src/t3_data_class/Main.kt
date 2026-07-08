package t3_data_class


// Задача: доменная модель заказа.
// data class Product(val id: String, val name: String, val price: Double)
// data class OrderItem(val product: Product, val quantity: Int)
// data class Order(val id: String, val items: List<OrderItem>, val status: OrderStatus)
// Функция fun Order.total(): Double — сумма по всем items
// Показать copy(): написать функцию fun Order.markAsPaid(): Order, которая возвращает новый Order с изменённым статусом, не трогая исходный
// Destructuring: data class Pair2(val first: Double, val second: Double), распаковать через val (a, b) = ...
// Проверить руками (или тестом), что equals у двух Order с одинаковыми полями возвращает true, а toString() печатает читаемо

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
}