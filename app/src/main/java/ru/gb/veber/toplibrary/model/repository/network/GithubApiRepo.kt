package ru.gb.veber.toplibrary.model.repository.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gb.veber.toplibrary.model.network.ReposDto
import ru.gb.veber.toplibrary.model.network.UsersDto

interface GithubApiRepo {

    fun getAllUsers(): Single<List<UsersDto>>

    fun getUser(@Path("login") login: String): Single<UsersDto>

    fun getRepos(@Path("login") login: String): Single<List<ReposDto>>

    suspend fun getCoroutineUsers(): List<UsersDto>
}