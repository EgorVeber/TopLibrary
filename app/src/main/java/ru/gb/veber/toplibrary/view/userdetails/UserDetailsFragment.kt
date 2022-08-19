package ru.gb.veber.toplibrary.view.userdetails

import android.os.Bundle
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
import ru.gb.veber.toplibrary.presenter.UserPresenter
import ru.gb.veber.toplibrary.view.main.BackPressedListener


class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.instance.router, arguments?.getParcelable(KEY_USER))
    }

    private lateinit var binding: FragmentUserScreenBinding

    companion object {
        const val KEY_USER = "KEY_USER"
        fun newInstance(bundle: Bundle) = UserDetailsFragment().apply { arguments = bundle }
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

    override fun onBackPressed() = presenter.onBackPressed()

    override fun setUser(login: String?) {
        binding.userName.text = login
    }
}