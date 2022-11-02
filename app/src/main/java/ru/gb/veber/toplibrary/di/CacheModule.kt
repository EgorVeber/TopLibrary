package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.repository.Cacheable
import ru.gb.veber.toplibrary.model.repository.RoomCache
import javax.inject.Singleton

@Module
object CacheModule {
    @Singleton
    @Provides
    fun cacheable(usersDao: UsersDao): Cacheable {
        return RoomCache(usersDao)
    }
}
