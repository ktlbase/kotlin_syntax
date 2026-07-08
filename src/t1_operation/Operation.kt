package t1_operation

// Результат операции
sealed class Result {
    data class Success(val a: Double) : Result()
    data class Error(val e: String) : Result()
}

// Операция
sealed class Operation {
    // Выполнить
    abstract fun exec(): Result

    // Сложить
    data class Add(val a: Double, val b: Double) : Operation() {
        override fun exec(): Result = Result.Success(a + b)
    }

    // Вычесть
    data class Subtract(val a: Double, val b: Double) : Operation() {
        override fun exec(): Result = Result.Success(a - b)

    }

    // Умножить
    data class Multiply(val a: Double, val b: Double) : Operation() {
        override fun exec(): Result = Result.Success(a * b)
    }

    // Разделить
    data class Divide(val a: Double, val b: Double) : Operation() {
        override fun exec(): Result {
            if (b == .0) {
                return Result.Error("Divide on zero value")
            }
            return Result.Success(a / b)
        }
    }
}