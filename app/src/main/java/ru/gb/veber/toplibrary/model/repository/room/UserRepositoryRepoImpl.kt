package ru.gb.veber.toplibrary.model.repository.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.UserWithReposDBObject
import ru.gb.veber.toplibrary.model.database.dao.UserRepoDao
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity

class UserRepositoryRepoImpl(private val userRepoDao: UserRepoDao) : UserRepositoryRepo {
    override fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable {
        return userRepoDao.insertAllRepos(userRepoDbEntity)
    }

    override fun queryForAllRepos(): Single<List<UserRepoDbEntity>> {
        return userRepoDao.queryForAllRepos()
    }

    override fun getUsersWithRepos(login: String): Single<UserWithReposDBObject> {
        return userRepoDao.getUsersWithRepos(login)
    }
}