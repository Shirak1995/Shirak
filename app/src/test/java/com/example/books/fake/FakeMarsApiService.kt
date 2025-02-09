package com.example.books.fake

import com.example.books.network.MarsApiService
import com.example.books.network.MarsPhoto

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}