package t2_extensions

// Сумирует элементы массива
fun List<Int>.sum(): Int {
    return fold(0){ acc, el -> acc + el}
}

// Находит среднее значение
fun List<Int>.customAverage(): Double {
    return sum().toDouble() / size
}

