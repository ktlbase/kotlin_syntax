package t1_operation

import org.junit.jupiter.api.Test


object TestOperations{
    fun add(a: Double=1.0, b:Double=2.0) = Operation.Add(a,b).exec()
    fun subtract(a: Double=1.0, b: Double=2.0) = Operation.Subtract(a,b).exec()
    fun multiply(a:Double=1.0, b: Double=2.0)= Operation.Multiply(a,b).exec()
    fun divide(a:Double=1.0, b: Double=2.0)= Operation.Divide(a,b).exec()
    fun divideByZero(a:Double=2.0, b: Double=.0)= Operation.Divide(a,b).exec()
}

fun assertSuccess(result: Result, expected: Double){
    assert(result is Result.Success && result.a == expected)
}

fun assertError(result: Result){
    assert(result is Result.Error)
}

class OperationTest{

    @Test
    fun `Деление на ноль выдает ошибку`(){
        assertError(TestOperations.divideByZero())
    }

    @Test
    fun `Сложение возвращает верный результат`(){
        assertSuccess(TestOperations.add(2.0, 3.0), 5.0)
    }

    @Test
    fun `Вычитание возвращает верный результат`(){
        assertSuccess(TestOperations.subtract(5.0, 3.0), 2.0)
    }

    @Test
    fun `Умножение возвращает верный результат`(){
        assertSuccess(TestOperations.multiply(4.0, 3.0), 12.0)
    }

    @Test
    fun `Деление возвращает верный результат`(){
        assertSuccess(TestOperations.divide(6.0, 3.0), 2.0)
    }

    @Test
    fun `Использование операций вместе`(){
        val o1 = TestOperations.add(1.0, 2.0) as Result.Success
        val o2 = TestOperations.subtract(3.0, 4.0) as Result.Success

        val o3 = TestOperations.multiply(o1.a, o2.a)

        assertSuccess(o3, -3.0)
    }

}