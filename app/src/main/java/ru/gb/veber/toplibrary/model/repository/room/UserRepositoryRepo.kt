package ru.gb.veber.toplibrary.model.repository.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.UserWithReposDBObject
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity

interface UserRepositoryRepo {
    fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable
    fun queryForAllRepos(): Single<List<UserRepoDbEntity>>
    fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>
}