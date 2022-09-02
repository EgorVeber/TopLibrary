package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.database.UserDAO
import ru.gb.veber.toplibrary.database.UserWithReposDBObject
import ru.gb.veber.toplibrary.model.data.GithubUser
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.model.network.UsersApi
import ru.gb.veber.toplibrary.utils.*
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl(
    private val usersApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus: Single<Boolean>,
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) fetchFromApi(true)
            else getFromDb()
        }
    }

    override fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }

    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return usersApi.getAllUsers().doCompletableIf(shouldPersist) {
            userDao.insertAll(it.map(::mapToDBObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getFromDb(): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(::mapToEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GithubUser> {
        return userDao.getUsersWithRepos(login).map {userWithRepos->
            val user = mapToEntity(userWithRepos.userDbObject)
            user.repos = userWithRepos.repos.map { mapRepos(it) }
            user
        }
    }


    //    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
//        return usersApi.getAllUsers().flatMap {
//            userDao.insertAll(it.map(::mapToDBObject)).andThen(Single.just(it))
//        }.map { it.map(::mapToEntity) }
//    }
}
