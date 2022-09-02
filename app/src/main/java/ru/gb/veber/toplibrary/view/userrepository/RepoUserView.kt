package ru.gb.veber.toplibrary.view.userrepository

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.gb.veber.toplibrary.network.ReposDto

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoUserView : MvpView {
    fun showRepo(repo:ReposDto)
}
