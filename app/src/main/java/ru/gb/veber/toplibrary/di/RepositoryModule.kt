package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.room.UserRepositoryRepo
import ru.gb.veber.toplibrary.model.repository.room.UsersRepo
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        githubApiRepo: GithubApiRepo,
        usersRepo: UsersRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): GithubRepository {
        return GithubRepositoryImpl(
            githubApiRepo,
            usersRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}
