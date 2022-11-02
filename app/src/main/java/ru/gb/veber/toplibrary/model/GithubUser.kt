package ru.gb.veber.toplibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.gb.veber.toplibrary.model.network.ReposDto

@Parcelize
data class GithubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
    var repos: List<ReposDto>? = null,
) : Parcelable
