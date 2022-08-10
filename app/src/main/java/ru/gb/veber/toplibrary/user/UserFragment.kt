package ru.gb.veber.toplibrary.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.App
import ru.gb.veber.toplibrary.core.BackPressedListener
import ru.gb.veber.toplibrary.databinding.FragmentUserListBinding
import ru.gb.veber.toplibrary.main.UserAdapter
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.imp.GithubRepositoryImpl

class UserFragment : MvpAppCompatFragment(), UserView, BackPressedListener {

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubRepositoryImpl(),
            App.instance.router)
    }
    private val userAdapter = UserAdapter()
    private lateinit var binding: FragmentUserListBinding

    companion object {
        //Передать аргументы
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGithubUser.adapter = userAdapter
        binding.rvGithubUser.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initList(list: List<GithubUser>) {
        userAdapter.users = list
    }

    override fun onBackPressed() = presenter.onBackPressed()
}