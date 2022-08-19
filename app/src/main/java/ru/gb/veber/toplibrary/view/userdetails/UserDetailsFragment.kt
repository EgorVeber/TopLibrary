package ru.gb.veber.toplibrary.view.userdetails

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
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
    private val bag = CompositeDisposable()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RxJava()
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun setUser(login: String?) {
        // binding.userName.text = login
    }

    private fun RxJava() {
        val observableNames = Observable.just("Jack", "Nikki", "Amia")


        //subscribeOn где будет рраюотать сам Observable create  io бэкроунд поток есть пулл потоков
        Single.create<String> {
            it.onSuccess("asdsa")
        }.subscribeByDefault()
            .subscribe({
                binding.userName.text = it
            }, {
                binding.userName.text = it.message
            }).disposebleBy(bag)//Отменяет все

        //flatMap
//        observableNames.flatMap { element ->
//            val delay = Random.nextInt(1000)
//            return@flatMap getUserInfo(element).delay(delay.toLong(), TimeUnit.MILLISECONDS)
//        }.observeOn(AndroidSchedulers.mainThread()).subscribe {
//            binding.userName.text = it.toString()
//        }
    }

    private fun <T> Single<T>.subscribeByDefault(): Single<T> {
        return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    private fun Disposable.disposebleBy(bag: CompositeDisposable) {
        bag.add(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

    private fun getUserInfo(name: String): Observable<List<String>> {
        return Observable.just(listOf(name, "email.com"))
    }
}