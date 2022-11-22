package ru.gb.veber.toplibrary.model.repository.network

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.network.GithubApi
import ru.gb.veber.toplibrary.model.network.ReposDto
import ru.gb.veber.toplibrary.model.network.UsersDto

class GithubApiRepoImpl(private val githubApi: GithubApi) : GithubApiRepo {
    override fun getAllUsers(): Single<List<UsersDto>> {
        return githubApi.getAllUsers()
    }

    override fun getUser(login: String): Single<UsersDto> {
        return githubApi.getUser(login)
    }

    override fun getRepos(login: String): Single<List<ReposDto>> {
        return githubApi.getRepos(login)
    }

    override suspend fun getCoroutineUsers(): List<UsersDto> {
        return githubApi.getCoroutineUsers()
    }
}