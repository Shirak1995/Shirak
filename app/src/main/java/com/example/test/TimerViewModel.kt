package com.example.test

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.*

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    private var countDownTimer: CountDownTimer? = null
    private var remainingTimeInMillis: Long = 0L
    private var isPaused = false

    // Состояние для оставшегося времени
    var timeLeft by mutableStateOf("00:00")
        private set
    var isTimerRunning by mutableStateOf(false)
        private set

    fun startTimer(durationMinutes: Int) {
        if (isTimerRunning) return // Если таймер уже работает, ничего не делать

        if (!isPaused) {
            remainingTimeInMillis = (durationMinutes * 60 * 1000).toLong()
        }

        countDownTimer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
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
        isPaused = false

        // Отправляем сервис для работы в фоновом режиме
        startTimerService(remainingTimeInMillis)
    }

    fun stopTimer() {
        if (isTimerRunning) {
            countDownTimer?.cancel()
            isPaused = true
            isTimerRunning = false

            // Останавливаем сервис
            val intent = Intent(getApplication(), TimerService::class.java)
            getApplication<Application>().stopService(intent)
        }
    }

    fun resetTimer() {
        stopTimer()
        remainingTimeInMillis = 0L
        timeLeft = "00:00"
        isPaused = false
    }

    private fun startTimerService(timeInMillis: Long) {
        val context = getApplication<Application>().applicationContext
        val intent = Intent(context, TimerService::class.java)
        intent.putExtra("duration", timeInMillis)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }
}
