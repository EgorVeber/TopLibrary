package ru.gb.veber.toplibrary.core

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.mirkhusainov.geekbrainscourse.core.utils.ConnetivityListener
import ru.gb.veber.toplibrary.model.database.GithubDB

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val router = cicerone.router
    val navigationHolder = cicerone.getNavigatorHolder()

    private lateinit var connetivityListener: ConnetivityListener

    val database: GithubDB by lazy { GithubDB.create(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this
        connetivityListener = ConnetivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }

    fun getConnectObservable() = connetivityListener.status()
    fun getConnectSingle() = connetivityListener.statusSingle()

    companion object {
        lateinit var instance: App
    }
}