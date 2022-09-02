package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.network.ReposDto
import ru.gb.veber.toplibrary.network.UsersApi
import ru.gb.veber.toplibrary.utils.mapToEntity
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl(private val usersApi: UsersApi) : GithubRepository {
    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers().map { it.map(::mapToEntity) }.delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }
}
