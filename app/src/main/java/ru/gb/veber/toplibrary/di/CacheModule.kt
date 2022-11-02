package ru.gb.veber.toplibrary.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.toplibrary.model.database.UserDAO
import ru.gb.veber.toplibrary.model.repository.Cacheable
import ru.gb.veber.toplibrary.model.repository.RoomCache
import javax.inject.Singleton

@Module
object CacheModule {
    @Singleton
    @Provides
    fun cacheable(userDAO: UserDAO): Cacheable {
        return RoomCache(userDAO)
    }
}