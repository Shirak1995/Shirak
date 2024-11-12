package com.example.amphibians.domain

import com.example.amphibians.data.model.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET ("amphibians_notvalid_url")
    suspend fun getAmphibians(): List<Amphibian>
}