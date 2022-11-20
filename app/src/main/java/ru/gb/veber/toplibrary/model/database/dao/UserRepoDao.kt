package ru.gb.veber.toplibrary.model.database.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.UserWithReposDBObject
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity

@Dao
abstract class UserRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable

    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<UserRepoDbEntity>>

    @Transaction
    @Query("Select * from users where login =:login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>
} 