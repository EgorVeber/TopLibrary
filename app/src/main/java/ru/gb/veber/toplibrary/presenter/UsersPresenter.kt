package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.model.repository.screen.UsersRepoScreen
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.users.UserView
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<UserView>() {

    @Inject
    lateinit var repository: UsersRepoScreen

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        App.instance.appComponent.inject(this)
        repository.getUsers().subscribeByDefault()
            .subscribe({
                viewState.initList(it)
                viewState.hideLoading()
            }, {
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