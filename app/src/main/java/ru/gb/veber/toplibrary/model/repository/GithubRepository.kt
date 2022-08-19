package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.gb.veber.toplibrary.model.GithubUser

interface GithubRepository {
    fun getUsers(): Observable<List<GithubUser>>
}