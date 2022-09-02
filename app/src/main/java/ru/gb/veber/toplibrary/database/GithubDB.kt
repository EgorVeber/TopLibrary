package ru.gb.veber.toplibrary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserDbObject::class, RepoDBObject::class), version = 1, exportSchema = false)

abstract class GithubDB : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        fun create(context: Context): GithubDB {
            return Room.databaseBuilder(context, GithubDB::class.java, "github.db").build()
        }
    }
}