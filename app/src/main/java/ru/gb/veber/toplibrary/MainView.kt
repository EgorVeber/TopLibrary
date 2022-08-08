package ru.gb.veber.toplibrary

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.gb.veber.toplibrary.model.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun initList(list: List<GithubUser>)
}