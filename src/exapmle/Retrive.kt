package com.example.exapmle

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class AddRetrieve(private val kodein: Kodein) : Calc {
    private val default by kodein.instance<Double>()
    override fun calc(d1: Double, d2: Double): Double {
        return d1 + d2
    }

    override fun calcDefault(d: Double): Double {
        return default + d
    }
}

val kodeinRetrieve = Kodein {
    bind<Calc>() with provider { AddRetrieve(kodein) }
    bind<Double>() with provider { 10.0 }
}

fun main() {
    val add: Calc by kodeinRetrieve.instance<Calc>()
    println("result ${add.calc(1.0, 2.0)}")
    println("result ${add.calcDefault(1.0)}")
}