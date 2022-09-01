package ru.gb.veber.toplibrary.utils

import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.network.UsersDto


fun mapToEntity(dto: UsersDto): GithubUser {
    return GithubUser(id = dto.id, login = dto.login, avatarUrl = dto.avatarUrl)
}

