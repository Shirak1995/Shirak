package com.example.test

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.TimerScreen
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
        }

        setContent {
            TestTheme {
                val timerViewModel: TimerViewModel = viewModel()
                TimerScreen(timerViewModel)
            }
        }

    }

    private fun requestPermissions() {
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            // Handle permission result
        }.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}