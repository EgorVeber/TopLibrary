package ru.gb.veber.toplibrary.user

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.repository.imp.GithubRepository

class UserPresenter(
    private val repository: GithubRepository,
    private val router: Router,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}