package ru.gb.veber.toplibrary.utils

import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.database.entity.UserRepoDbEntity
import ru.gb.veber.toplibrary.model.database.entity.UsersDbEntity
import ru.gb.veber.toplibrary.model.network.ReposDto
import ru.gb.veber.toplibrary.model.network.UsersDto


fun mapToEntity(dto: UsersDto): GithubUser {
    return GithubUser(id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl)
}

fun mapToDBObject(dto: UsersDto): UsersDbEntity {
    return UsersDbEntity(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToEntity(usersDbEntity: UsersDbEntity): GithubUser {
    return GithubUser(id = usersDbEntity.id,
        login = usersDbEntity.login,
        avatarUrl = usersDbEntity.avatarUrl,
        reposUrl = usersDbEntity.reposUrl)
}

fun mapRepos(repoDto: UserRepoDbEntity): ReposDto {
    return ReposDto(
        id = repoDto.id,
        forksCount = repoDto.forks,
        name = repoDto.name,
        nodeId = repoDto.nodeId,
        createdAt = repoDto.createdAt,
        description = repoDto.description,
        language = repoDto.language,
        updatedAt = repoDto.updatedAt
    )
}

fun mapReposToObject(repoDto: ReposDto, mUserId: Int): UserRepoDbEntity {
    return UserRepoDbEntity(
        id = repoDto.id,
        forks = repoDto.forksCount,
        name = repoDto.name,
        userId = mUserId,
        language = repoDto.language,
        description = repoDto.description,
        createdAt = repoDto.createdAt,
        nodeId = repoDto.nodeId,
        updatedAt = repoDto.updatedAt
    )
}



