package com.example.thirtydaysofcinema.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thirtydaysofcinema.R

data class Movie(
    @DrawableRes val imageRes: Int,
    @StringRes val day: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)

val movies = listOf(
    Movie(R.drawable.the_dark_knight, R.string.day_1, R.string.movie_1, R.string.description_1),
    Movie(R.drawable.harry_potter, R.string.day_2, R.string.movie_2, R.string.description_2),
    Movie(R.drawable.terminator, R.string.day_3, R.string.movie_3, R.string.description_3),
    Movie(R.drawable.avengers, R.string.day_4, R.string.movie_4, R.string.description_4),
    Movie(R.drawable.game_of_thrones, R.string.day_5, R.string.movie_5, R.string.description_5),
    Movie(R.drawable.prison_break, R.string.day_6, R.string.movie_6, R.string.description_6),
    Movie(R.drawable.gladiator, R.string.day_7, R.string.movie_7, R.string.description_7),
    Movie(R.drawable.interstellar, R.string.day_8, R.string.movie_8, R.string.description_8),
    Movie(R.drawable.coco, R.string.day_9, R.string.movie_9, R.string.description_9),
    Movie(R.drawable.sherlock, R.string.day_10, R.string.movie_10, R.string.description_10)
)
