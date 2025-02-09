/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.football.data

import com.example.football.R
import com.example.football.model.Player
import com.example.football.model.Position

/**
 * Footballs data
 */
object LocalFootballDataProvider{
    val defaultPosition = getPositionsData()[0]
    val defaultPlayer = getPlayersData(defaultPosition)[0]

    fun getPositionsData(): List<Position> {
        return listOf(
            Position(
                id = 1,
                titleResourceId = R.string.goalkeeper,
                positionDetailsId = R.string.goalkeeper_details,
                imageResourceId = R.drawable.gk,
            ),
            Position(
                id = 2,
                titleResourceId = R.string.defender,
                positionDetailsId = R.string.defender_details,
                imageResourceId = R.drawable.def,
            ),
            Position(
                id = 3,
                titleResourceId = R.string.midfielder,
                positionDetailsId = R.string.midfielder_details,
                imageResourceId = R.drawable.mf,
            ),
            Position(
                id = 4,
                titleResourceId = R.string.forward,
                positionDetailsId = R.string.forward_details,
                imageResourceId = R.drawable.frw,
            )
        )
    }

    fun getPlayersData(selectedPosition: Position): List<Player> {
        return when(selectedPosition.id) {
            1 -> listOf(
                Player(id = 1, nameId = R.string.neuer, shortInfoId = R.string.neuer_short_info, imageResourceId = R.drawable.neuer_little, posterResourceId = R.drawable.neuer, honoursId = R.string.neuer_honours),
                Player(id = 2, nameId = R.string.buffon, shortInfoId = R.string.buffon_short_info, imageResourceId = R.drawable.buffon_little, posterResourceId = R.drawable.buffon, honoursId = R.string.neuer_honours),
                Player(id = 3, nameId = R.string.van_der_sar, shortInfoId = R.string.van_der_sar_short_info, imageResourceId = R.drawable.van_der_sar_little, posterResourceId = R.drawable.van_der_sar, honoursId = R.string.neuer_honours),
                Player(id = 4, nameId = R.string.casillas, shortInfoId = R.string.casillas_short_info, imageResourceId = R.drawable.casillas_little, posterResourceId = R.drawable.casillas, honoursId = R.string.neuer_honours),
                Player(id = 5, nameId = R.string.cech, shortInfoId = R.string.chech_short_info, imageResourceId = R.drawable.cech_little, posterResourceId = R.drawable.cech, honoursId = R.string.neuer_honours)
            )
            2 -> listOf(
                Player(id = 6, nameId = R.string.maldini, shortInfoId = R.string.maldini_short_info, imageResourceId = R.drawable.maldini_little, posterResourceId = R.drawable.maldini, honoursId = R.string.neuer_honours),
                Player(id = 7, nameId = R.string.vidic, shortInfoId = R.string.vidic_short_info, imageResourceId = R.drawable.vidic_little, posterResourceId = R.drawable.vidic, honoursId = R.string.neuer_honours),
                Player(id = 8, nameId = R.string.zanetti, shortInfoId = R.string.zanetti_short_info, imageResourceId = R.drawable.zanetti_little, posterResourceId = R.drawable.zanetti, honoursId = R.string.neuer_honours),
                Player(id = 9, nameId = R.string.ferdinand, shortInfoId = R.string.ferdinand_short_info, imageResourceId = R.drawable.ferdinand_little, posterResourceId = R.drawable.ferdinand, honoursId = R.string.neuer_honours),
                Player(id = 10, nameId = R.string.puyol, shortInfoId = R.string.puyol_short_info, imageResourceId = R.drawable.puyol_little, posterResourceId = R.drawable.puyol, honoursId = R.string.neuer_honours)
            )
            3 -> listOf(
                Player(id = 11, nameId = R.string.scholes, shortInfoId = R.string.scholes_short_info, imageResourceId = R.drawable.scholes_little, posterResourceId = R.drawable.scholes, honoursId = R.string.neuer_honours),
                Player(id = 12, nameId = R.string.kaka, shortInfoId = R.string.kaka_short_info, imageResourceId = R.drawable.kaka_little, posterResourceId = R.drawable.kaka, honoursId = R.string.neuer_honours),
                Player(id = 13, nameId = R.string.iniesta, shortInfoId = R.string.iniesta_short_info, imageResourceId = R.drawable.iniesta_little, posterResourceId = R.drawable.iniesta, honoursId = R.string.neuer_honours),
                Player(id = 14, nameId = R.string.kroos, shortInfoId = R.string.kroos_short_info, imageResourceId = R.drawable.kroos_little, posterResourceId = R.drawable.kroos, honoursId = R.string.neuer_honours),
                Player(id = 15, nameId = R.string.lampard, shortInfoId = R.string.lampard_short_info, imageResourceId = R.drawable.lampard_little, posterResourceId = R.drawable.lampard, honoursId = R.string.neuer_honours)
            )
            else -> listOf(
                Player(id = 16, nameId = R.string.rooney, shortInfoId = R.string.rooney_short_info, imageResourceId = R.drawable.rooney_little, posterResourceId = R.drawable.rooney, honoursId = R.string.neuer_honours),
                Player(id = 17, nameId = R.string.rvn, shortInfoId = R.string.rvn_short_info, imageResourceId = R.drawable.rvn_little, posterResourceId = R.drawable.rvn, honoursId = R.string.neuer_honours),
                Player(id = 18, nameId = R.string.henry, shortInfoId = R.string.henry_short_info, imageResourceId = R.drawable.henry_little, posterResourceId = R.drawable.henry, honoursId = R.string.neuer_honours),
                Player(id = 19, nameId = R.string.del_piero, shortInfoId = R.string.del_piero_short_info, imageResourceId = R.drawable.del_piero_little, posterResourceId = R.drawable.del_piero, honoursId = R.string.neuer_honours),
                Player(id = 20, nameId = R.string.suarez, shortInfoId = R.string.suarez_short_info, imageResourceId = R.drawable.suarez_little, posterResourceId = R.drawable.suarez, honoursId = R.string.neuer_honours)
            )
        }
    }
}
