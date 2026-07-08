package delegates

val heavyData: List<Int> by lazy {
    // При первом обращении будет выполнено
    println("Loading heavy data...")
    (1..1_000_000).toList()
}
fun main(){
    println("Starting...")
    println("Size: ${heavyData.size}")
    println("Size again: ${heavyData.size}")

}