package ru.gb.veber.toplibrary.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.gb.veber.toplibrary.model.database.dao.UserRepoDao
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity

@Database(entities = [UsersDbEntity::class, UserRepoDbEntity::class],
    version = 1,
    exportSchema = false)

abstract class GithubDB : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun userRepoDao(): UserRepoDao

    companion object {
        fun create(context: Context): GithubDB {
            return Room.databaseBuilder(context, GithubDB::class.java, "github.db").build()
        }
    }
}