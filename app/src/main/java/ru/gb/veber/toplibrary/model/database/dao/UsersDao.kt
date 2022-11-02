package ru.gb.veber.toplibrary.model.database.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.UserWithReposDBObject
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity

@Dao
abstract class UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(usersDbEntity: List<UsersDbEntity>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UsersDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(userRepoDbEntity: List<UserRepoDbEntity>): Completable


    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<UserRepoDbEntity>>

    @Transaction
    @Query("Select * from users where login =:login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>

}