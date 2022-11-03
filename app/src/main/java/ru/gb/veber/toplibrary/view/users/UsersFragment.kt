package ru.gb.veber.toplibrary.view.users

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.FragmentUserListBinding
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.presenter.UsersPresenter
import ru.gb.veber.toplibrary.utils.hide
import ru.gb.veber.toplibrary.utils.show
import ru.gb.veber.toplibrary.view.main.BackPressedListener


class UsersFragment : MvpAppCompatFragment(), UserView, BackPressedListener {


    private val userAdapter = UserAdapter {
        presenter.openUserScreen(it)
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter()
    }

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
        binding.rvGithubUser.adapter = userAdapter
        binding.rvGithubUser.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initList(list: List<GithubUser>) {
        TransitionManager.beginDelayedTransition(binding.root)
        userAdapter.users = list
    }

    override fun showLoading() {
        binding.progressBarList.show()
    }

    override fun hideLoading() {
        binding.progressBarList.hide()
    }

    override fun errorGetUser(message: String?) {
        Log.d("TAG", "errorGetUser() called with: message = $message")
    }

    override fun onBackPressed() = presenter.onBackPressed()
}