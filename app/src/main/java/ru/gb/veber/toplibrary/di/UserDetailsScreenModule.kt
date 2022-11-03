package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.room.UserRepositoryRepo
import ru.gb.veber.toplibrary.model.repository.screen.UserDetailsRepoScreen
import ru.gb.veber.toplibrary.model.repository.screen.UserDetailsRepoScreenImpl
import javax.inject.Singleton

@Module
object UserDetailsScreenModule {

    @Provides
    @Singleton
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