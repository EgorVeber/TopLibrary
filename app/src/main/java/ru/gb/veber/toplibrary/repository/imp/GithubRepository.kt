package ru.gb.veber.toplibrary.repository.imp

import ru.gb.veber.toplibrary.model.GithubUser

interface GithubRepository {
    fun getUsers():List<GithubUser>
}