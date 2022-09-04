package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.UserDAO
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
            if (hasConnection) getUsersApi(true)
            else getUsersBD()
        }
    }

    override fun getUserWithReposByLogin(login: String): Single<Pair<GithubUser, List<ReposDto>>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getUserWithRepoApi(login)
            } else {
                getUserWithReposBD(login)
            }
        }
    }

    override fun checkStatusNetwork(): Single<Boolean> {
       return networkStatus
    }

    private fun getUsersApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return usersApi.getAllUsers().doCompletableIf(shouldPersist) {
            userDao.insertAll(it.map(::mapToDBObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getUsersBD(): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(::mapToEntity) }
    }


    private fun getUserWithReposBD(login: String): Single<Pair<GithubUser, List<ReposDto>>> {
        return userDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.userDbObject)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            Pair(user, user.repos!!.sortedByDescending { it.createdAt })
        }
    }


    private fun getUserWithRepoApi(login: String): Single<Pair<GithubUser, List<ReposDto>>> {
        return Single.zip(getUserByLogin(login),
            getReposByLogin(login)) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            Pair<GithubUser, List<ReposDto>>(user,
                repos.sortedByDescending { it.createdAt })
        }.doCompletableIf(true) { pair ->
            userDao.insertAllRepos(pair.second.map {
                mapReposToObject(it, pair.first.id)
            })
        }
    }

    private fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    private fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }
}
