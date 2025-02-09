package com.example.football.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Player (
    val id: Int,
    @StringRes val nameId: Int,
    @StringRes val shortInfoId: Int,
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val posterResourceId: Int,
    @StringRes val honoursId: Int,
)