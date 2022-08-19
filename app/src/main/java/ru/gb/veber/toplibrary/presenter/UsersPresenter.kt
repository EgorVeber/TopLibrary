package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.view.users.UserView

class UsersPresenter(
    private val repository: GithubRepository,
    private val router: Router,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.initList(it)
                viewState.hideLoading()
            },
                {
                    viewState.errorGetUser(it.message)
                })
    }

    fun openUserScreen(user: GithubUser) {
        router.navigateTo(UserScreen(user))
    }

    fun onBackPressed(): Boolean {
        router.backTo(UsersScreen)
        return true
    }
}