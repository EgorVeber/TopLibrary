package ru.gb.veber.toplibrary.di
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.model.database.GithubDB
import ru.gb.veber.toplibrary.model.database.UserDAO
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun database(context: Context): GithubDB =
        GithubDB.create(context)

    @Singleton
    @Provides
    fun userDao(database: GithubDB): UserDAO =
        database.userDao()
}
