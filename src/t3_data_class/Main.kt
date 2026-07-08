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
}