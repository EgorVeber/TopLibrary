package ru.gb.veber.toplibrary

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter {
        CounterPresenter(CountersModel())
    }

    private val listener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonOne -> {
                presenter.clickButtonOne()
            }
            R.id.buttonTwo -> {
                presenter.clickButtonTwo()
            }
            R.id.buttonThree -> {
                presenter.clickButtonThree()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        buttonOne.setOnClickListener(listener)
        buttonTwo.setOnClickListener(listener)
        buttonThree.setOnClickListener(listener)
    }

    override fun setTextButtonOne(text: String) = with(binding) {
        buttonOne.text = text
    }

    override fun setTextButtonTwo(text: String) {
        binding.buttonTwo.text = text
    }

    override fun setTextButtonThree(text: String) {
        binding.buttonThree.text = text
    }
}