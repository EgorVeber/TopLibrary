package ru.gb.veber.toplibrary.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.utils.disposebleBy
import ru.gb.veber.toplibrary.utils.subscribeByDefault
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsView
import javax.inject.Inject

class UserDetailsPresenter() : MvpPresenter<UserDetailsView>() {

    @Inject lateinit var repository: GithubRepository
    @Inject lateinit var router: Router

    private val bag = CompositeDisposable()
    private var mLogin: String? = null

    fun loadUser(login: String) {
        mLogin = login
        viewState.showLoading()
        repository.getUserWithReposByLogin(login).subscribeByDefault().subscribe({
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

