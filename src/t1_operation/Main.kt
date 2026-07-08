package t1_operation

// Sealed class Operation с вариантами
// сложения, вычитания, умножения, деления (Double).
// Divide возвращает Result.Error при делении на ноль.
fun main(){
    val operations = listOf(
        Operation.Add(3.0, 2.0),
        Operation.Subtract(3.0, 2.0),
        Operation.Multiply(3.0, 2.0),
        Operation.Divide(3.0, 2.0),
        Operation.Divide(3.0, .0)
    )

    for (op in operations) {
        val description = when (op) {
            is Operation.Add -> "${op.a} + ${op.b}"
            is Operation.Subtract -> "${op.a} - ${op.b}"
            is Operation.Multiply -> "${op.a} * ${op.b}"
            is Operation.Divide -> "${op.a} / ${op.b}"
        }

        when (val result = op.exec()) {
            is Result.Success -> println("$description = ${result.a}")
            is Result.Error -> println("$description error: ${result.e}")
        }
    }
}