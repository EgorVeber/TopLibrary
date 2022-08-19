package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.gb.veber.toplibrary.model.GithubUser
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl : GithubRepository {
    private val repositories = listOf(
        GithubUser("User1"),
        GithubUser("User2"),
        GithubUser("User3"),
        GithubUser("User4"),
        GithubUser("User5")
    )

    override fun getUsers(): Observable<List<GithubUser>> {
        return Observable.fromIterable(listOf(repositories)).delay(1,TimeUnit.SECONDS)
    }
}