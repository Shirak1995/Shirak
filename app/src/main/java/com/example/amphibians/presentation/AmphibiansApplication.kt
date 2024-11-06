package com.example.amphibians.presentation

import android.app.Application
import com.example.amphibians.domain.AppContainer
import com.example.amphibians.domain.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}