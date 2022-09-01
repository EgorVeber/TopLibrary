package ru.gb.veber.toplibrary.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.GithubUserRepos
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.utils.disposebleBy
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsView

class UserDetailsPresenter(
    private val router: Router,
    private val repository: GithubRepository,
) : MvpPresenter<UserDetailsView>() {


    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        viewState.showLoading()
        Single.zip(repository.getUserByLogin(login),
            repository.getReposByLogin(login)) { user, repos ->
            GithubUserRepos(user, repos.sortedByDescending { it.createdAt })
        }.subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.showUser(it)
        }, {
            Log.d("TAG", it.localizedMessage)
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

