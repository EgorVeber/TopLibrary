package ru.gb.veber.toplibrary.model.repository.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity

interface UsersRepo {
     fun insertAll(usersDbEntity: List<UsersDbEntity>): Completable
     fun queryForAllUsers(): Single<List<UsersDbEntity>>
}