package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.view.userdetails.UserScreenView

class UserPresenter(
    private val router: Router,
    private val user: GithubUser?,
) : MvpPresenter<UserScreenView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUser(user?.login)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}