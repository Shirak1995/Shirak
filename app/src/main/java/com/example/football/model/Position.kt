package com.example.football.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Position (
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val positionDetailsId: Int,
    @DrawableRes val imageResourceId: Int
)