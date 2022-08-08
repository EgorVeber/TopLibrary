package ru.gb.veber.toplibrary

class CountersModel {
    private val counters = mutableListOf(0, 0, 0)

    private fun getCounter(index: Int) = counters[index]

    fun counterAddValue(index: Int): Int {
        counters[index]++
        return getCounter(index)
    }

    fun setCounterValue(index: Int, value: Int) {
        counters[index] = value
    }
}