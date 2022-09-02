package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.view.userrepository.RepoUserView

class RepoUserPresenter(
    private val router: Router, private val repo: ReposDto?,
) : MvpPresenter<RepoUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}