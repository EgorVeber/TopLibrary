package ru.gb.veber.toplibrary.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userDbObject: UserDbObject): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(userDbObject: List<UserDbObject>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UserDbObject>>

    @Delete
    abstract fun delete(userDbObject: UserDbObject): Completable

    @Transaction
    @Query("Select * from users where login =:login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>


//    @Transaction
//    fun getUsersWithRepos(login: String): UserDbObject {
//        //Атомарно без потери данных старый способ
//        val user = queryForUserByLogin(login)
//        user.repos = queryForAllRepos(user.id)
//        return user
//    }

//    @Query("Select * from users Where login = :login")
//    abstract fun queryForUserByLogin(login: String): UserDbObject
//
//    @Query("Select * from repos Where userId =:userId")
//    abstract fun queryForAllRepos(userId: Int): List<RepoDBObject>
}