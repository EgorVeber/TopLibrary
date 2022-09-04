package ru.gb.veber.toplibrary.model.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(userDbObject: List<UserDbObject>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(repoDBObject: List<RepoDBObject>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UserDbObject>>

    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<RepoDBObject>>

    @Transaction
    @Query("Select * from users where login =:login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>


//    @Query("Delete from repos")
//    abstract fun deleteAllRepos(): Completable

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(userDbObject: UserDbObject): Completable

//    @Query("DELETE  from repos")
//    abstract fun queryForTest(): Completable

//    @Delete
//    abstract fun delete(userDbObject: UserDbObject): Completable

}