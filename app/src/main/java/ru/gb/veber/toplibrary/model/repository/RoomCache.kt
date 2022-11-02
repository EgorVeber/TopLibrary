package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity


class RoomCache(private val usersDao: UsersDao) : Cacheable {
    override fun insertRepoList(list: List<UserRepoDbEntity>): Completable {
        return usersDao.insertAllRepos(list)
    }

    override fun insertUserList(list: List<UsersDbEntity>): Completable {
        return usersDao.insertAll(list)
    }
}