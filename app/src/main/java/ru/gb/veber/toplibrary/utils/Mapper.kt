package ru.gb.veber.toplibrary.utils

import ru.gb.veber.toplibrary.database.RepoDBObject
import ru.gb.veber.toplibrary.database.UserDbObject
import ru.gb.veber.toplibrary.model.data.GithubUser
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.model.data.UsersDto


fun mapToEntity(dto: UsersDto): GithubUser {
    return GithubUser(id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl)
}

fun mapToDBObject(dto: UsersDto): UserDbObject {
    return UserDbObject(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToEntity(userDbObject: UserDbObject): GithubUser {
    return GithubUser(id = userDbObject.id,
        login = userDbObject.login,
        avatarUrl = userDbObject.avatarUrl,
        reposUrl = userDbObject.reposUrl)
}

fun mapRepos(repoDto: RepoDBObject): ReposDto {
    return ReposDto(
        id = repoDto.id,
        forksCount = repoDto.forks,
        name = repoDto.name,
        nodeId = "1",
        createdAt = "1",
        description = "1",
        language = "1",
        updatedAt = "1"
    )
}


