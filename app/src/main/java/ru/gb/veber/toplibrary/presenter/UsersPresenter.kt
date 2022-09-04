package ru.gb.veber.toplibrary.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Single.just
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.users.UserView
import java.util.*

class UsersPresenter(
    private val repository: GithubRepository,
    private val router: Router,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        Log.d("TAG", "onFirstViewAttach() called")
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