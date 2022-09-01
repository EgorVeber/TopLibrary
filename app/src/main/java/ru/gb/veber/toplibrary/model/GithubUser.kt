package ru.gb.veber.toplibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.gb.veber.toplibrary.network.ReposDto

@Parcelize
data class GithubUser(
    val id:Int,
    val login: String,
    val avatarUrl:String?,
    val reposUrl:String?,
) : Parcelable


@Parcelize
data class GithubUserRepos(
    val user:GithubUser,
    val reposList:List<ReposDto>
) : Parcelable
