package coroutines

import java.util.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toDuration

data class Order(
    val id: String,
    val total: Double,
)


class OrderLoadException : Exception("Failed to load order")

interface OrderRepository {
    suspend fun loadOrder(): Order
    suspend fun riskyLoadOrder(): Order
    suspend fun failingLoadOrder(): Order
    val ordersFlow: SharedFlow<Order>
    fun startFlow()
    fun stopFlow()
}

class OrderRepositoryImpl : OrderRepository {
    private val random = Random()
    private val scope = CoroutineScope(SupervisorJob())
    private var job: Job? = null

    private val _ordersFlow = MutableSharedFlow<Order>()
    override val ordersFlow: SharedFlow<Order> = _ordersFlow.asSharedFlow()

    override suspend fun loadOrder(): Order {
        val ms = (400..2000).random().milliseconds
        println("Loading order... (delay: $ms)")
        delay(ms)

        val orderId = random.nextInt(1000).toString()
        val orderTotal = random.nextDouble() * 100
        return Order(orderId, orderTotal)
    }

    override suspend fun riskyLoadOrder(): Order {
        val isSuccess = (1..3).random() < 3
        if (!isSuccess) {
            throw OrderLoadException()
        }

        return loadOrder()
    }

    override suspend fun failingLoadOrder(): Order {
        throw OrderLoadException()
    }

    override fun startFlow() {
        if (job?.isActive == true) return
        job = scope.launch {
            while (true) {
                val ms = (400..2000).random().milliseconds
                delay(ms)
                _ordersFlow.emit(loadOrder())
            }
        }
    }

    override fun stopFlow() {
        job?.cancel()
        job = null
    }
}
