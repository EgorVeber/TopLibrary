package ru.gb.veber.toplibrary.model.repository.screen

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser

interface UserDetailsRepoScreen {
    fun getUserWithReposByLogin(login: String): Single<GithubUser>
}