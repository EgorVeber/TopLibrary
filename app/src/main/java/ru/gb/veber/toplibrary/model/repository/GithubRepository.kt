package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUsersById(login:String): Single<GithubUser>
}