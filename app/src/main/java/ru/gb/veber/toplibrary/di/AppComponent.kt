package ru.gb.veber.toplibrary.di

import dagger.Component
import ru.gb.veber.toplibrary.presenter.MainPresenter
import ru.gb.veber.toplibrary.presenter.RepoUserPresenter
import ru.gb.veber.toplibrary.presenter.UsersPresenter
import ru.gb.veber.toplibrary.view.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        DataBaseModule::class,
        NavigationModule::class,
        RepoNetworkModule::class,
        UsersScreenModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent(): RepositorySubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoUserPresenter: RepoUserPresenter)
}
