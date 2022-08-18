package ru.gb.veber.toplibrary

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operators {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun createJust() = Observable.just("1", "2", "3", "3")
        fun createJust2() = Observable.just("4", "5", "6")
    }

    class Consumer(val producer: Producer) {

        fun exec() {
            //execTake()
            execFlatMap()
        }

        fun execZip() {
            val observable1 = Observable.just("1").delay(1, TimeUnit.SECONDS)
            val observable2 = Observable.just("2").delay(2, TimeUnit.SECONDS)
            val observable3 = Observable.just("3").delay(4, TimeUnit.SECONDS)
            val observable4 = Observable.just("4").delay(6, TimeUnit.SECONDS)

//            Observable.zip(observable1, observable2, observable3, observable4,
//                Function4<String, String, String, String, List<String>> { t1, t2, t3, t4 ->
//                    return@Function4 listOf(t1, t2, t3, t4)
//                })
//                .subscribeOn(Schedulers.computation())
//                .subscribe({
//                    println("Zip result: $it")
//                }, {
//                    println("onError: ${it.message}")
//                })
        }


        fun execFlatMap() {
            producer.createJust()
                .flatMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@flatMap Observable.just(it + "x").delay(delay,
                        TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }


        fun execTake() {
            //flatMap возвращает observable
            producer.createJust()
                //.take(2)// первые 2 элемента
                //.skip(2)//
                .map { it + it + "2" }
                .distinct()
                .filter { it.toInt() > 200 }
                .mergeWith(producer.createJust2())//Может быть не последовательно
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}