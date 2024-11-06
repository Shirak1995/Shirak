package com.example.amphibians.fake

import com.example.amphibians.domain.NetworkAmphibiansRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkAmphibiansRepositoryTest {
    @Test
    fun networkAmphibiansRepository_getAmphibians_verifyPhoto() =
        runTest {
            val repository = NetworkAmphibiansRepository(
                amphibiansApiService = FakeAmphibiansApiService()
            )
            assertEquals(FakeDataSource.amphibianList, repository.getAmphibians())
        }
}