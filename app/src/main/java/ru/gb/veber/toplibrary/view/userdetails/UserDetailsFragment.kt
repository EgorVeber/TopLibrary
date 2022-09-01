package ru.gb.veber.toplibrary.view.userdetails

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.FragmentUserScreenBinding
import ru.gb.veber.toplibrary.model.GithubUserRepos
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import ru.gb.veber.toplibrary.network.NetworkProvider
import ru.gb.veber.toplibrary.presenter.UserDetailsPresenter
import ru.gb.veber.toplibrary.utils.hide
import ru.gb.veber.toplibrary.utils.loadGlide
import ru.gb.veber.toplibrary.utils.show
import ru.gb.veber.toplibrary.view.main.BackPressedListener
import ru.gb.veber.toplibrary.view.users.UserAdapter


class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {


    private val reposAdapter = ReposAdapter {

    }


    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(App.instance.router,
            GithubRepositoryImpl(NetworkProvider.usersApi))
    }

    private var binding: FragmentUserScreenBinding? = null

    companion object {
        const val KEY_USER = "KEY_USER"
        fun newInstance(userLogin: String) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_USER, userLogin)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentUserScreenBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_USER)?.let {
            presenter.loadUser(it)
        }

        binding?.rvGithubUserRepos?.adapter = reposAdapter
        binding?.rvGithubUserRepos?.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun showUser(user: GithubUserRepos) {
        TransitionManager.beginDelayedTransition(binding?.root)
        binding?.userName?.text = user.user.login
        binding?.ivUserAvatar?.loadGlide(user.user.avatarUrl)
        reposAdapter.repos = user.reposList
    }

    override fun showLoading() {
        binding?.apply {
            progressBar.show()
            userName.hide()
            ivUserAvatar.hide()
        }
    }

    override fun hideLoading() {
        binding?.apply {
            progressBar.hide()
            userName.show()
            ivUserAvatar.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}