package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.utils.disposebleBy
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsView

class UserPresenter(
    private val router: Router,
    private val repository: GithubRepository,
) : MvpPresenter<UserDetailsView>() {


    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        viewState.showLoading()
        repository.getUsersById(login).subscribeByDefault()
            .subscribe({ user ->
                viewState.showUser(user)
                viewState.hideLoading()
            },
                {
                }).disposebleBy(bag)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}