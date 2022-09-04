package ru.gb.veber.toplibrary.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.users.UserView

class UsersPresenter(
    private val repository: GithubRepository,
    private val router: Router,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUsers().subscribeByDefault()
            .subscribe({
                Log.d("TAG", "subscribe")
                Log.d("TAG", "${it.size}")
                viewState.initList(it)
                viewState.hideLoading()
            },
                {
                    viewState.errorGetUser(it.message)
                })
    }

    fun openUserScreen(userLogin: String) {
        router.navigateTo(UserScreen(userLogin))
    }

    fun onBackPressed(): Boolean {
        router.backTo(UsersScreen)
        return true
    }
}