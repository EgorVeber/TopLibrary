package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.data.GithubUser
import ru.gb.veber.toplibrary.model.data.ReposDto

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserWithReposByLogin(login: String): Single<Pair<GithubUser, List<ReposDto>>>
    fun checkStatusNetwork(): Single<Boolean>
}