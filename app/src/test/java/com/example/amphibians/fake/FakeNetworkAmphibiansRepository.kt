package com.example.amphibians.fake

import com.example.amphibians.domain.AmphibiansRepository
import com.example.amphibians.data.model.Amphibian

class FakeNetworkAmphibiansRepository : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibianList
    }
}