package com.example.premierleagueclub

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Club(
    val name: String,
    val description: String,
    val photo: Int,
    val stadium: String,
    val keyPlayer: String,
    val manager: String,
    val foundedYear: String,
    val trophies: String,
    val website: String
) : Parcelable
