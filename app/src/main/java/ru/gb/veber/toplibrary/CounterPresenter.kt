package ru.gb.veber.toplibrary

import moxy.MvpPresenter

class CounterPresenter(
    private val model: CountersModel
) : MvpPresenter<MainView>() {

    fun clickButtonOne() {
        viewState.setTextButtonOne(model.counterAddValue(COUNTER_ONE).toString())
    }

    fun clickButtonTwo() {
        viewState.setTextButtonTwo(model.counterAddValue(COUNTER_TWO).toString())
    }

    fun clickButtonThree() {
        viewState.setTextButtonThree(model.counterAddValue(COUNTER_THREE).toString())
    }

    companion object {
        const val COUNTER_ONE = 0
        const val COUNTER_TWO = 1
        const val COUNTER_THREE = 2
    }
}