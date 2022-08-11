package ru.gb.veber.toplibrary.user

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.core.navigator.UserScreen
import ru.gb.veber.toplibrary.core.navigator.UsersScreen
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.imp.GithubRepository

class UsersPresenter(
    private val repository: GithubRepository,
    private val router: Router,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun openUserScreen(user: GithubUser) {
        router.navigateTo(UserScreen(user))
    }

    fun onBackPressed(): Boolean {
        router.backTo(UsersScreen)
        return true
    }
}