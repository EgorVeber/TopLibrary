package ru.gb.veber.toplibrary.model.repository.room

import io.reactivex.rxjava3.core.Completable
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity


class CacheableImpl(
    private val usersRepo: UsersRepo,
    private val userRepositoryRepo: UserRepositoryRepo,
) : Cacheable {
    override fun insertUserList(list: List<UsersDbEntity>): Completable {
        return usersRepo.insertAll(list)
    }

    override fun insertRepoList(list: List<UserRepoDbEntity>): Completable {
        return userRepositoryRepo.insertAllRepos(list)
    }
}