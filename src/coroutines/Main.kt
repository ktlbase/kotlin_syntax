package coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// suspend-функции с задержкой — loadOrder() с delay(), эмуляция асинхронной загрузки данных
// Обработка ошибок в корутинах — riskyLoadOrder() с вероятностным исключением, try/catch вокруг suspend-вызова
// Параллельный запуск через async/awaitAll — несколько riskyLoadOrder() одновременно вместо последовательного цикла, подсчёт успехов через count { it } без гонки данных
// Flow — MutableSharedFlow/SharedFlow, эмиссия заказов через startFlow(), подписка через collect в отдельной корутине (launch)

fun main() = runBlocking {
    val repository: OrderRepository = OrderRepositoryImpl()
    var start = System.currentTimeMillis()

    // Запустим поток пока делаем другие операции
    var handledOrders = 0

    val flowJob = launch {
        repository.ordersFlow.collect {
            handledOrders++
        }
    }
    repository.startFlow()

    // Последовательно
    repository.loadOrder()
    repository.loadOrder()
    repository.loadOrder()

    var end = System.currentTimeMillis()
    // LOGS: Total time taken: 3854 ms
    println("Total time taken: ${end - start} ms\n\n")

    // Последовательно
    start = System.currentTimeMillis()
    var successTries = 0;
    for (i in 1..5) {
        try {
            repository.riskyLoadOrder()
            successTries++
        } catch (e: OrderLoadException) {
        }
    }
    end = System.currentTimeMillis()
    println("Risky load stat: SUCCESS: $successTries, FAIL: ${5 - successTries}")
    // LOGS: Total time taken for risky load: 6431 ms
    println("Total time taken for risky load: ${end - start} ms\n\n")

    // Параллельно
    start = System.currentTimeMillis()
    successTries = (1..500).map {
        async {
            try {
                repository.riskyLoadOrder()
                true
            } catch (e: OrderLoadException) {
                false
            }
        }
    }.awaitAll().count { it }

    end = System.currentTimeMillis()
    println("Risky parallel load (500) stat: SUCCESS: $successTries, FAIL: ${500 - successTries}")
    // LOGS: Total time taken for risky parallel load: 2043 ms
    println("Total time taken for risky parallel load: ${end - start} ms\n\n")

    repository.stopFlow()
    flowJob.cancel()
    println("Handled orders: $handledOrders")


}