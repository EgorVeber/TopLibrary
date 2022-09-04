package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.gb.veber.toplibrary.model.database.RepoDBObject
import ru.gb.veber.toplibrary.model.database.UserDAO
import ru.gb.veber.toplibrary.model.database.UserDbObject


class RoomCache(private val userDao: UserDAO) : Cacheable {
    override fun insertRepoList(list: List<RepoDBObject>): Completable {
        return userDao.insertAllRepos(list)
    }

    override fun insertUserList(list: List<UserDbObject>): Completable {
        return userDao.insertAll(list)
    }
}