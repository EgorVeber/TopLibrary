package ru.gb.veber.toplibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        binding.buttonOne.setOnClickListener(listener)
        binding.buttonTwo.setOnClickListener(listener)
        binding.buttonThree.setOnClickListener(listener)

    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> {
                binding.buttonOne.text = text
            }
            1 -> {
                binding.buttonTwo.text = text
            }
            2 -> {
                binding.buttonThree.text = text
            }
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putIntArray("Array", counters.toIntArray())
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        savedInstanceState.getIntArray("Array")?.let {
//            counters.clear()
//            counters.addAll(it.toMutableList())
//            initView()
//        }
//    }
}