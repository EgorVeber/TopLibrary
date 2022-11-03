package ru.gb.veber.toplibrary.model.repository.screen

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.network.ReposDto
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.room.UserRepositoryRepo
import ru.gb.veber.toplibrary.utils.doCompletableIf
import ru.gb.veber.toplibrary.utils.mapRepos
import ru.gb.veber.toplibrary.utils.mapReposToObject
import ru.gb.veber.toplibrary.utils.mapToEntity

class UserDetailsRepoScreenImpl(
    private val githubApiRepo: GithubApiRepo,
    private val userRepositoryRepo: UserRepositoryRepo,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable,
) : UserDetailsRepoScreen {
    override fun getUserWithReposByLogin(login: String): Single<GithubUser> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getUserWithRepoApi(login)
            } else {
                getUserWithReposBD(login)
            }
        }
    }

    private fun getUserWithReposBD(login: String): Single<GithubUser> {
        return userRepositoryRepo.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.usersDbEntity)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            user
        }
    }


    private fun getUserWithRepoApi(login: String): Single<GithubUser> {
        return Single.zip(getUserByLogin(login),
            getReposByLogin(login)) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            user.repos = repos
            user
        }.doCompletableIf(true) { user ->
            user.repos?.let { repos ->
                roomCache.insertRepoList(repos.map { repo ->
                    mapReposToObject(repo, user.id)
                })
            } ?: Completable.create {
                it.onError(Throwable(message = "Repos is Empty"))
            }
        }
    }

    private fun getUserByLogin(login: String): Single<GithubUser> {
        return githubApiRepo.getUser(login).map(::mapToEntity)
    }

    private fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return githubApiRepo.getRepos(login)
    }
}