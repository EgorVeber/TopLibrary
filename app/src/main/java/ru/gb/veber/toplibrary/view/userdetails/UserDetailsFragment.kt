package ru.gb.veber.toplibrary.view.userdetails

import android.annotation.SuppressLint
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
import ru.gb.veber.toplibrary.model.data.GithubUser
import ru.gb.veber.toplibrary.presenter.UserDetailsPresenter
import ru.gb.veber.toplibrary.utils.hide
import ru.gb.veber.toplibrary.utils.loadGlide
import ru.gb.veber.toplibrary.utils.show
import ru.gb.veber.toplibrary.view.main.BackPressedListener


class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {


    private val reposAdapter = ReposAdapter {
        presenter.openRepoScreen(it)
    }

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
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

    @SuppressLint("SetTextI18n")
    override fun showUser(user: GithubUser) {
        TransitionManager.beginDelayedTransition(binding?.root)
        binding?.userName?.text = user.login
        binding?.ivUserAvatar?.loadGlide(user.avatarUrl)
        binding?.userRepos?.text = "Repo:" + user.repos?.size.toString()
        user.repos?.let {
            reposAdapter.repos = it
        }
    }

    override fun showLoading() {
        binding?.apply {
            progressBar.show()
            userName.hide()
            ivUserAvatar.hide()
            rvGithubUserRepos.hide()
            userRepos.hide()
        }
    }

    override fun hideLoading() {
        binding?.apply {
            progressBar.hide()
            userName.show()
            ivUserAvatar.show()
            rvGithubUserRepos.show()
            userRepos.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}