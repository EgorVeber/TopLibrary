package ru.gb.veber.toplibrary.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.gb.veber.toplibrary.model.database.RepoDBObject
import ru.gb.veber.toplibrary.model.database.UserDbObject

interface Cacheable {
    fun insertRepoList(list: List<RepoDBObject>): Completable
    fun insertUserList(list: List<UserDbObject>): Completable
}
