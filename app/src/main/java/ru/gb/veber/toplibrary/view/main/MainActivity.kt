package ru.gb.veber.toplibrary.view.main

import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.R
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding
import ru.gb.veber.toplibrary.presenter.MainPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.instance.navigationHolder.removeNavigator()
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