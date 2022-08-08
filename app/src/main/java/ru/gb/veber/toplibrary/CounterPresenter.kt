package ru.gb.veber.toplibrary

import moxy.MvpPresenter
import ru.gb.veber.toplibrary.repository.imp.GithubRepository

class CounterPresenter(
    private val repository: GithubRepository,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }
}