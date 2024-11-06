package com.example.amphibians.fake

import com.example.amphibians.rules.TestDispatcherRule
import com.example.amphibians.presentation.AmphibiansUiState
import com.example.amphibians.presentation.AmphibianViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AmphibianViewModelTest {
    @get: Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun amphibiansViewModel_getAmphibians_verifyAmphibiansUiStateSuccess() =
        runTest {
            val amphibianViewModel = AmphibianViewModel(
                amphibiansRepository = FakeNetworkAmphibiansRepository()
            )
            assertEquals(AmphibiansUiState.Success(FakeDataSource.amphibianList), amphibianViewModel.amphibiansUiState)
        }
}