package ru.gb.veber.toplibrary.model.database

import androidx.room.Embedded
import androidx.room.Relation
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity

data class UserWithReposDBObject(
    @Embedded
    val usersDbEntity: UsersDbEntity,
    @Relation(
        parentColumn = UsersDbEntity.PRIMARY_KEY,
        entityColumn = UserRepoDbEntity.FOREIGN_USER_KEY
    )
    val repos: List<UserRepoDbEntity>,
)