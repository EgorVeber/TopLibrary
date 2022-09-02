package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.network.ReposDto

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUser>
    fun getReposByLogin(login: String): Single<List<ReposDto>>
}