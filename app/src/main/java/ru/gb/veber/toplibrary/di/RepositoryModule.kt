package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.database.dao.UserRepoDao
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.repository.Cacheable
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepoImpl
import javax.inject.Singleton


@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        githubApiRepo: GithubApiRepo,
        usersDao: UsersDao,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
        userRepoDao: UserRepoDao,
    ): GithubRepository {
        return GithubRepositoryImpl(githubApiRepo,
            usersDao,
            networkStatus.statusSingle(),
            cacheable,
            userRepoDao)
    }
}
