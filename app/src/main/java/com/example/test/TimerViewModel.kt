package com.example.test

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    private var countDownTimer: CountDownTimer? = null
    private var initialTimeInMillis: Long = 0L

    // Состояние для оставшегося времени
    var timeLeft by mutableStateOf("00:00")
        private set
    var isTimerRunning by mutableStateOf(false)
        private set

    fun startTimer(durationMinutes: Int) {
        if (isTimerRunning) return // Если таймер уже работает, ничего не делать

        initialTimeInMillis = (durationMinutes * 60 * 1000).toLong()

        // Запуск таймера
        countDownTimer = object : CountDownTimer(initialTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutesLeft = (millisUntilFinished / 1000) / 60
                val secondsLeft = (millisUntilFinished / 1000) % 60
                timeLeft = String.format("%02d:%02d", minutesLeft, secondsLeft)
            }

            override fun onFinish() {
                timeLeft = "00:00"
                isTimerRunning = false
            }
        }
        countDownTimer?.start()
        isTimerRunning = true

        // Отправляем сервис для работы в фоновом режиме
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
        countDownTimer?.cancel()
        timeLeft = "00:00"
        isTimerRunning = false

        // Останавливаем сервис
        val intent = Intent(getApplication(), TimerService::class.java)
        getApplication<Application>().stopService(intent)
    }

    fun resetTimer() {
        stopTimer()
        timeLeft = "00:00"
    }
}
