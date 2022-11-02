package ru.gb.veber.toplibrary.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.model.database.GithubDB
import ru.gb.veber.toplibrary.model.database.dao.UserRepoDao
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun database(context: Context): GithubDB =
        GithubDB.create(context)

    @Singleton
    @Provides
    fun usersDao(database: GithubDB): UsersDao =
        database.usersDao()

    @Singleton
    @Provides
    fun userRepoDao(database: GithubDB): UserRepoDao =
        database.userRepoDao()
}
