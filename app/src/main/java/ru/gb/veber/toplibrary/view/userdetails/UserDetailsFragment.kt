package ru.gb.veber.toplibrary.view.userdetails

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.FragmentUserScreenBinding
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import ru.gb.veber.toplibrary.network.NetworkProvider
import ru.gb.veber.toplibrary.presenter.UserPresenter
import ru.gb.veber.toplibrary.presenter.UsersPresenter
import ru.gb.veber.toplibrary.utils.hide
import ru.gb.veber.toplibrary.utils.loadGlide
import ru.gb.veber.toplibrary.utils.show
import ru.gb.veber.toplibrary.view.main.BackPressedListener


class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.instance.router,
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
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun showUser(user: GithubUser) {
        TransitionManager.beginDelayedTransition(binding?.root)
        binding?.userName?.text = user.login
        binding?.ivUserAvatar?.loadGlide(user.avatarUrl)
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