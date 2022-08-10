package ru.gb.veber.toplibrary.model.repository.imp

import ru.gb.veber.toplibrary.model.GithubUser

class GithubRepositoryImpl : GithubRepository {
    private val repositories = listOf(
        GithubUser("User1"),
        GithubUser("User2"),
        GithubUser("User3"),
        GithubUser("User4"),
        GithubUser("User5")
    )

   override fun getUsers(): List<GithubUser> {
        return repositories
    }
}