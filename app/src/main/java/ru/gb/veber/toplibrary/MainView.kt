package ru.gb.veber.toplibrary

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView:MvpView {
    fun setTextButtonOne(text: String)
    fun setTextButtonTwo(text: String)
    fun setTextButtonThree(text: String)
}