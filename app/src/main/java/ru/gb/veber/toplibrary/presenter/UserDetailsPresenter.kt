package ru.gb.veber.toplibrary.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.data.GithubUser
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.utils.disposebleBy
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsView

class UserDetailsPresenter(
    private val router: Router,
    private val repository: GithubRepository,
) : MvpPresenter<UserDetailsView>() {


    private val bag = CompositeDisposable()
    private var mLogin: String? = null
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        mLogin = login
        viewState.showLoading()
        Single.zip(repository.getUserByLogin(login),
            repository.getReposByLogin(login)) { user, repos ->

            repos.sortedByDescending { it.createdAt }.map {
                it.createdAt = it.createdAt.substring(0, 10)
                it
            }

            Pair<GithubUser, List<ReposDto>>(user,repos)
        }.subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.showUser(it)
        }, {
            Log.d("TAG", it.localizedMessage)
        }).disposebleBy(bag)
    }

    fun onBackPressed(): Boolean {
        mLogin?.let {
            router.backTo(UserScreen(it))
        }
        return true
    }

    fun openRepoScreen(repo: ReposDto) {
        router.navigateTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}

