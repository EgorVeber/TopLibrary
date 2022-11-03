package ru.gb.veber.toplibrary.core

import android.app.Application
import ru.gb.veber.toplibrary.di.*

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

}