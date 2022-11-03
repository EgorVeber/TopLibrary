package ru.gb.veber.toplibrary.model.repository.screen

import io.reactivex.rxjava3.core.Single
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.repository.network.GithubApiRepo
import ru.gb.veber.toplibrary.model.repository.room.Cacheable
import ru.gb.veber.toplibrary.model.repository.room.UsersRepo
import ru.gb.veber.toplibrary.utils.doCompletableIf
import ru.gb.veber.toplibrary.utils.mapToDBObject
import ru.gb.veber.toplibrary.utils.mapToEntity

class UsersRepoScreenImpl(
    private val githubApiRepo: GithubApiRepo,
    private val usersRepo: UsersRepo,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable,
) : UsersRepoScreen {
    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) getUsersApi(true)
            else getUsersBD()
        }
    }

    private fun getUsersApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return githubApiRepo.getAllUsers().doCompletableIf(shouldPersist) {
            roomCache.insertUserList(it.map(::mapToDBObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getUsersBD(): Single<List<GithubUser>> {
        return usersRepo.queryForAllUsers().map { it.map(::mapToEntity) }
    }
}