package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.core.ConnectivityListener
import ru.gb.veber.toplibrary.model.database.UserDAO
import ru.gb.veber.toplibrary.model.network.UsersApi
import ru.gb.veber.toplibrary.model.repository.GithubRepository
import ru.gb.veber.toplibrary.model.repository.GithubRepositoryImpl
import javax.inject.Singleton


@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        usersApi: UsersApi,
        userDao: UserDAO,
        networkStatus: ConnectivityListener,
    ): GithubRepository {
        return GithubRepositoryImpl(usersApi, userDao, networkStatus.statusSingle())
    }
}
