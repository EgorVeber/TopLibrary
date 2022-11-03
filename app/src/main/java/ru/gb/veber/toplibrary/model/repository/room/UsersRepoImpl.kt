package ru.gb.veber.toplibrary.model.repository.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.dao.UsersDao
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity

class UsersRepoImpl(private val usersDao: UsersDao) : UsersRepo {
    override fun insertAll(usersDbEntity: List<UsersDbEntity>): Completable {
        return usersDao.insertAll(usersDbEntity)
    }

    override fun queryForAllUsers(): Single<List<UsersDbEntity>> {
        return usersDao.queryForAllUsers()
    }
}