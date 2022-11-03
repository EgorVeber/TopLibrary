package ru.gb.veber.toplibrary.model.repository.room

import io.reactivex.rxjava3.core.Completable
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity

interface Cacheable {
    fun insertRepoList(list: List<UserRepoDbEntity>): Completable
    fun insertUserList(list: List<UsersDbEntity>): Completable
}
