package presenter

class Box<in T> {
    fun consume(t: T) {
        println(t)
    }
}

class Producer<T> {
    fun doSome(t: MutableCollection<T>): MutableCollection<T> {
        return t
    }
}

fun main() {

//    var boxOfInt = Box<Int>(null)
    var producerOfInt: Producer<Int> = Producer<Int>()

    var producerOfNumber: Producer<Number> = Producer<Number>()

    producerOfInt.doSome(mutableListOf<Int>(1, 2, 3))
//    producerOfInt.doSome(mutableListOf<Number>(1, 2, 3))
    producerOfNumber.doSome(mutableListOf<Number>(1, 2, 3))
//    producerOfNumber.doSome(mutableListOf<Int>(1, 2, 3))

    val producerOfNumberOut: Producer<out Number> = producerOfInt
}
