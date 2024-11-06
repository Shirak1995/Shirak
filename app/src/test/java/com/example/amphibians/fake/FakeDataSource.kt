package com.example.amphibians.fake

import com.example.amphibians.data.model.Amphibian

object FakeDataSource {
    const val nameOne = "name1"
    const val nameTwo = "name2"
    const val typeOne = "type1"
    const val typeTwo = "type2"
    const val descriptionOne = "description1"
    const val descriptionTwo = "description2"
    const val imgOne = "url.1"
    const val imgTwo = "url.2"
    val amphibianList  = listOf(
        Amphibian(name = nameOne, type = typeOne, description = descriptionOne, imgSrc = imgOne),
        Amphibian(name = nameTwo, type = typeTwo, description = descriptionTwo, imgSrc = imgTwo)
    )
}