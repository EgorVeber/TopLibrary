package ru.gb.veber.toplibrary.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.model.database.GithubDB
import ru.gb.veber.toplibrary.model.database.dao.UserRepoDao
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.room.CacheableImpl
import ru.gb.veber.toplibrary.model.repository.room.UserRepositoryRepo
import ru.gb.veber.toplibrary.model.repository.room.UserRepositoryRepoImpl
import ru.gb.veber.toplibrary.model.repository.room.UsersRepo
import ru.gb.veber.toplibrary.model.repository.room.UsersRepoImpl
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun database(context: Context): GithubDB =
        GithubDB.create(context)

    @Singleton
    @Provides
    fun usersDao(database: GithubDB): UsersRepo =
        UsersRepoImpl(database.usersDao())

    @Singleton
    @Provides
    fun userRepoDao(database: GithubDB): UserRepositoryRepo =
        UserRepositoryRepoImpl(database.userRepoDao())

    @Singleton
    @Provides
    fun cacheable(usersRepo: UsersRepo, userRepositoryRepo: UserRepositoryRepo): Cacheable {
        return CacheableImpl(usersRepo, userRepositoryRepo)
    }
}
