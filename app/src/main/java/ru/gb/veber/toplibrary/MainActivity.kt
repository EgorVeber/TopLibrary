package ru.gb.veber.toplibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val counters = mutableListOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOne.setOnClickListener(clickListener)
        binding.buttonTwo.setOnClickListener(clickListener)
        binding.buttonThree.setOnClickListener(clickListener)

        initView()
    }

    private fun initView() {
        binding.buttonOne.text = counters[0].toString()
        binding.buttonTwo.text = counters[1].toString()
        binding.buttonThree.text = counters[2].toString()
    }

    private var clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonOne -> {
                binding.buttonOne.text = (++counters[0]).toString()
            }
            R.id.buttonTwo -> {
                binding.buttonTwo.text = (++counters[1]).toString()
            }
            R.id.buttonThree -> {
                binding.buttonThree.text = (++counters[2]).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("Array", counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getIntArray("Array")?.let {
            counters.clear()
            counters.addAll(it.toMutableList())
            initView()
        }
    }
}