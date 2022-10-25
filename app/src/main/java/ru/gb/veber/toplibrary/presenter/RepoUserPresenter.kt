package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.view.userrepository.RepoUserView
import javax.inject.Inject

class RepoUserPresenter(
    private val repo: ReposDto?,
) : MvpPresenter<RepoUserView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}