package ru.gb.veber.toplibrary

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun clickButtonOne() {
        view.setTextButtonOne(model.counterAddValue(COUNTER_ONE).toString())
        saveCounters()
    }

    fun clickButtonTwo() {
        view.setTextButtonTwo(model.counterAddValue(COUNTER_TWO).toString())
        saveCounters()
    }

    fun clickButtonThree() {
        view.setTextButtonThree(model.counterAddValue(COUNTER_THREE).toString())
        saveCounters()
    }

    private fun saveCounters() = view.saveCounters(model.getCounters())

    fun setCounters(list: List<Int>) {
        model.setCounterValue(COUNTER_ONE, list[COUNTER_ONE])
        model.setCounterValue(COUNTER_TWO, list[COUNTER_TWO])
        model.setCounterValue(COUNTER_THREE, list[COUNTER_THREE])
        view.setTextButtonOne(model.getCounter(COUNTER_ONE).toString())
        view.setTextButtonTwo(model.getCounter(COUNTER_TWO).toString())
        view.setTextButtonThree(model.getCounter(COUNTER_THREE).toString())
    }

    companion object {
        const val COUNTER_ONE = 0
        const val COUNTER_TWO = 1
        const val COUNTER_THREE = 2
    }
}