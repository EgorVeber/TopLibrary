package ru.gb.veber.toplibrary

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)

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

        if (savedInstanceState == null) {
            presenter.setCounters(getCountersShared())
        }
    }

    private fun init() {
        with(binding) {
            buttonOne.setOnClickListener(listener)
            buttonTwo.setOnClickListener(listener)
            buttonThree.setOnClickListener(listener)
        }
    }

    override fun setTextButtonOne(text: String) {
        binding.buttonOne.text = text
    }

    override fun setTextButtonTwo(text: String) {
        binding.buttonTwo.text = text
    }

    override fun setTextButtonThree(text: String) {
        binding.buttonThree.text = text
    }

    override fun saveCounters(counters: MutableList<Int>) {
        getPreferences(MODE_PRIVATE).edit().putString(KEY_COUNTERS, Gson().toJson(counters)).apply()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.setCounters(getCountersShared())
    }

    private fun getCountersShared(): List<Int> {
        try {
            return Gson().fromJson(getPreferences(MODE_PRIVATE).getString(KEY_COUNTERS, "{}"),
                object : TypeToken<List<Int?>?>() {}.type)
        } catch (e: Exception) {
        }
        return listOf(0, 0, 0)
    }

    companion object {
        const val KEY_COUNTERS = "KEY_COUNTERS"
    }
}