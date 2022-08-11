package ru.gb.veber.toplibrary.usercreen

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserScreenView : MvpView {
    fun setUser(login: String?)
}
