package ru.gb.veber.toplibrary.user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.gb.veber.toplibrary.model.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun initList(list: List<GithubUser>)
}