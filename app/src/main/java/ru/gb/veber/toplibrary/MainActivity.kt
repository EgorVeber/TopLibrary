package ru.gb.veber.toplibrary

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding
import ru.gb.veber.toplibrary.main.UserAdapter
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.repository.imp.GithubRepositoryImpl

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()

    private val presenter by moxyPresenter { CounterPresenter(GithubRepositoryImpl()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGithubUser.layoutManager = LinearLayoutManager(this@MainActivity)
        //binding.rvGithubUser.setItemViewCacheSize(2)//Опитмизация сколько view закешированно
        binding.rvGithubUser.adapter = adapter
    }

    override fun initList(list: List<GithubUser>) {
        adapter.users = list
    }
}