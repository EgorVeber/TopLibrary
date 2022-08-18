package ru.gb.veber.toplibrary.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.FragmentUserListBinding
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import ru.gb.veber.toplibrary.presenter.UsersPresenter
import ru.gb.veber.toplibrary.view.main.BackPressedListener

class UsersFragment : MvpAppCompatFragment(), UserView, BackPressedListener {

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubRepositoryImpl(),
            App.instance.router)
    }

    private val listener = object : ItemClickListener {
        override fun onUserClick(user: GithubUser) {
            presenter.openUserScreen(user)
        }
    }

    private val userAdapter = UserAdapter()
    private lateinit var binding: FragmentUserListBinding

    companion object {
        fun getInstance(): UsersFragment {
            return UsersFragment()
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
        userAdapter.setOnUserClickListener(listener)
        binding.rvGithubUser.adapter = userAdapter
        binding.rvGithubUser.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun initList(list: List<GithubUser>) {
        userAdapter.users = list
    }
    override fun onBackPressed() = presenter.onBackPressed()

}