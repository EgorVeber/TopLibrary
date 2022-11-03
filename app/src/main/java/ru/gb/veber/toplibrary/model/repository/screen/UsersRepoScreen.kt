package ru.gb.veber.toplibrary.model.repository.screen

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser

interface UsersRepoScreen {
    fun getUsers(): Single<List<GithubUser>>
}