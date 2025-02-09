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

package com.example.football.ui

import androidx.lifecycle.ViewModel
import com.example.football.data.LocalFootballDataProvider
import com.example.football.model.Player
import com.example.football.model.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * View Model for Football app
 */
class FootballViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        FootballUiState(
            positionsList = LocalFootballDataProvider.getPositionsData(),
            currentPosition = LocalFootballDataProvider.getPositionsData().getOrElse(0) {
                LocalFootballDataProvider.defaultPosition
            },
            playersList = LocalFootballDataProvider.getPlayersData(LocalFootballDataProvider.defaultPosition),
            currentPlayer = LocalFootballDataProvider.getPlayersData(LocalFootballDataProvider.getPositionsData().getOrElse(0) {
                LocalFootballDataProvider.defaultPosition
            })[0]
        )
    )
    val uiState: StateFlow<FootballUiState> = _uiState

    fun updateCurrentPosition(selectedPosition: Position) {
        _uiState.update {
            it.copy(currentPosition = selectedPosition, currentPlayer = LocalFootballDataProvider.getPlayersData(selectedPosition)[0])
        }
    }

    fun updateCurrentPlayer(selectedPlayer: Player) {
        _uiState.update {
            it.copy(currentPlayer = selectedPlayer)
        }
    }

    fun updateCurrentPlayerForDetail(selectedPosition: Position) {
        _uiState.update {
            it.copy(currentPlayer = LocalFootballDataProvider.getPlayersData(selectedPosition)[0])
        }
    }

    fun navigateToPlayersListPage(selectedPosition: Position) {
        _uiState.update {
            it.copy(isShowingListPage = true, isPositionsListPage = false, playersList = LocalFootballDataProvider.getPlayersData(selectedPosition))
        }
    }

    fun navigateToDetailPage(selectedPlayer: Player) {
        _uiState.update {
            it.copy(isShowingListPage = false, currentPlayer = selectedPlayer)
        }
    }

    fun navigateToPreviousPage(isShowingListPage: Boolean, isPositionsListPage: Boolean) {
        if (!isShowingListPage) {
            _uiState.update {
                it.copy(isShowingListPage = true, isPositionsListPage = false)
            }
        } else {
            _uiState.update {
                it.copy(isShowingListPage = true, isPositionsListPage = true)
            }
        }
    }
}

data class FootballUiState(
    val positionsList: List<Position> = emptyList(),
    val currentPosition: Position = LocalFootballDataProvider.defaultPosition,
    val playersList: List<Player> = emptyList(),
    val currentPlayer: Player = LocalFootballDataProvider.defaultPlayer,
    val isShowingListPage: Boolean = true,
    val isPositionsListPage: Boolean = true
)
