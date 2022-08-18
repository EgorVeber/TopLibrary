package ru.gb.veber.toplibrary.model.repository

import ru.gb.veber.toplibrary.model.GithubUser

interface GithubRepository {
    fun getUsers():List<GithubUser>
}