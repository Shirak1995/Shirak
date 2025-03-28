package com.example.test

import android.app.Application
import android.content.Intent
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TimerViewModel(application: Application) : AndroidViewModel(application) {
    fun startTimer(durationMinutes: Int) {
        val context = getApplication<Application>().applicationContext
        val intent = Intent(context, TimerService::class.java)
        intent.putExtra("duration", durationMinutes * 60 * 1000L)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }


    fun stopTimer() {
        val intent = Intent(getApplication(), TimerService::class.java)
        getApplication<Application>().stopService(intent)
    }
}