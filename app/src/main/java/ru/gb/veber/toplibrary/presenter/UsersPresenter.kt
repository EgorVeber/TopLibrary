package ru.gb.veber.toplibrary.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.users.UserView
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<UserView>() {

    @Inject lateinit var repository: GithubRepository
    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)

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