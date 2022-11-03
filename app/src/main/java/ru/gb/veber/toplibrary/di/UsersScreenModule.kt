package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.room.UsersRepo
import ru.gb.veber.toplibrary.model.repository.screen.UsersRepoScreen
import ru.gb.veber.toplibrary.model.repository.screen.UsersRepoScreenImpl
import javax.inject.Singleton

@Module()
object UsersScreenModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        githubApiRepo: GithubApiRepo,
        usersRepo: UsersRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): UsersRepoScreen {
        return UsersRepoScreenImpl(
            githubApiRepo,
            usersRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}


