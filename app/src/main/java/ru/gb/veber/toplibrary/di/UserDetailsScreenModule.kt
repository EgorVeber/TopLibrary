package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.room.UserRepositoryRepo
import ru.gb.veber.toplibrary.model.repository.screen.UserDetailsRepoScreen
import ru.gb.veber.toplibrary.model.repository.screen.UserDetailsRepoScreenImpl
import ru.gb.veber.toplibrary.presenter.UserDetailsPresenter
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryScope


@Module
open class UserDetailsScreenModule {
    @Provides
    @RepositoryScope
    fun provideUserDetailsRepository(
        githubApiRepo: GithubApiRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): UserDetailsRepoScreen {
        return UserDetailsRepoScreenImpl(
            githubApiRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}

@RepositoryScope
@Subcomponent(
    modules = [
        UserDetailsScreenModule::class
    ]
)

interface RepositorySubcomponent {
    fun inject(userPresenter: UserDetailsPresenter)
}



