package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.network.UsersApi
import ru.gb.veber.toplibrary.model.repository.Cacheable
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import javax.inject.Singleton


@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        usersApi: UsersApi,
        usersDao: UsersDao,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): GithubRepository {
        return GithubRepositoryImpl(usersApi, usersDao, networkStatus.statusSingle(), cacheable)
    }
}
