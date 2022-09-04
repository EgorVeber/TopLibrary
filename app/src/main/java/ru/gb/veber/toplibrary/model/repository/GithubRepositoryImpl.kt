package ru.gb.veber.toplibrary.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.database.UserDAO
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

    override fun getUserWithReposByLogin(login: String): Single<Pair<GithubUser, List<ReposDto>>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getZip(login)
            } else {
                getUserWithRepos(login)
            }
        }
    }


    fun getZip(login: String) =
        Single.zip(getUserByLogin(login),
            getReposByLogin(login)) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            Log.d("TAG", "getZip() called with: user = $user, repos = $repos")
            Pair<GithubUser, List<ReposDto>>(user,
                repos.sortedByDescending { it.createdAt })
        }.doCompletableIf(true) { pair ->
            userDao.insertAllRepos(pair.second.map {
                mapReposToObject(it, pair.first.id)
            })
        }

    fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    fun getReposByLogin(login: String): Single<List<ReposDto>> {
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

    override fun getUserWithRepos(login: String): Single<Pair<GithubUser, List<ReposDto>>> {
        return userDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.userDbObject)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            Pair(user, user.repos!!.sortedByDescending { it.createdAt })
        }
    }
}
