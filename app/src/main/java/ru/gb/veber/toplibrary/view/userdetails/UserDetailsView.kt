package ru.gb.veber.toplibrary.view.userdetails

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.gb.veber.toplibrary.model.GithubUserRepos

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun showUser(user: GithubUserRepos)
    fun showLoading()
    fun hideLoading()
}
