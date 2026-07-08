package delegates

import kotlin.properties.Delegates

interface Logger {
    fun log(message: String)
}

class ConsoleLogger : Logger {
    override fun log(message: String) {
        println("Log: $message")
    }
}
// --------------------------------------

interface OrderRepository : Logger {
    // Метод для создания заказа
    fun createOrder()
    // Счетчик актуальных заказов
    val counter: Int
}

/// Делегирование реализации интерфейса Logger объекту logger.
class OrderRepositoryImpl(
    private val logger: Logger,
) : OrderRepository, Logger by logger {

    override fun createOrder() {
        log("Creating order...")
        // Логика создания заказа
        counter += 1
        log("Order created.")
    }

    override var counter: Int by Delegates.observable(0) { property, oldValue, newValue ->
        log("Counter changed from $oldValue to $newValue")
    }
}

fun main() {
    val logger: Logger = ConsoleLogger()
    val orderRepository: OrderRepository = OrderRepositoryImpl(logger)

    orderRepository.createOrder()
    orderRepository.createOrder()
    orderRepository.createOrder()
}