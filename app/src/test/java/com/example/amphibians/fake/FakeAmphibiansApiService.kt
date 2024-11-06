package com.example.amphibians.fake

import com.example.amphibians.domain.AmphibiansApiService
import com.example.amphibians.data.model.Amphibian

class FakeAmphibiansApiService : AmphibiansApiService {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibianList
    }
}