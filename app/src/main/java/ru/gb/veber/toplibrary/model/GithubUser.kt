package ru.gb.veber.toplibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id:Int,
    val login: String,
    val avatarUrl:String?
) : Parcelable
