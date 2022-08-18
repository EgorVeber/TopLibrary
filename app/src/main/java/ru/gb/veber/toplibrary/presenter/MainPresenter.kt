package ru.gb.veber.toplibrary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.veber.toplibrary.Creation
import ru.gb.veber.toplibrary.Operators
import ru.gb.veber.toplibrary.view.main.MainView

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //router.replaceScreen(UsersScreen)
       // Creation().exec()
        Operators().exec()
    }

    fun onBackPressed() {
        router.exit()
    }
}