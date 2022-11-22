package ru.gb.veber.toplibrary.view.main

import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.R
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding
import ru.gb.veber.toplibrary.model.network.GithubApi
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepoImpl
import ru.gb.veber.toplibrary.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.containerMain)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder


    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is BackPressedListener && fragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}

abstract class TestMy {
    abstract fun getDate()
    fun getDate2() {
        Log.d("TAG", "getDate2() called")
    }
}

interface TestMy2 {
    fun getDate()
    fun getDate2() {
        Log.d("TAG", "getDate2() called")
    }
}