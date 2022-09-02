package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.database.UserWithReposDBObject
import ru.gb.veber.toplibrary.model.data.GithubUser
import ru.gb.veber.toplibrary.model.data.ReposDto

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUser>
    fun getReposByLogin(login: String): Single<List<ReposDto>>
    fun getUserWithRepos(login: String): Single<GithubUser>
}