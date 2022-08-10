package ru.gb.veber.toplibrary.main

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.core.navigator.UsersScreen

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen)
    }

    fun onBackPressed() {
        router.exit()
    }
}