package ru.gb.veber.toplibrary.view.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.gb.veber.toplibrary.model.data.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun initList(list: List<GithubUser>)
    fun showLoading()
    fun hideLoading()
    fun errorGetUser(message: String?)
}

interface ItemClickListener {
    fun onUserClick(userLogin: String)
}
