package com.example.test

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.*

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    private var countDownTimer: CountDownTimer? = null
    var remainingTimeInMillis by mutableStateOf(0L) // Сохраняем оставшееся время
        private set
    var isPaused by mutableStateOf(false) // Сделали публичным с возможностью только внутреннего изменения
        private set
    var timeLeft by mutableStateOf("00:00")
        private set
    var isTimerRunning by mutableStateOf(false)
        private set

    fun startTimer(durationMinutes: Int? = null) {
        if (isTimerRunning) return

        // Если таймер запущен впервые или был сброшен - берем новое значение
        if (!isPaused) {
            remainingTimeInMillis = (durationMinutes ?: 1) * 60 * 1000L
        }

        Log.d("TimerViewModel", "Старт таймера. Оставшееся время: $remainingTimeInMillis мс")

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
                //remainingTimeInMillis = 0L
            }
        }
        countDownTimer?.start()
        isTimerRunning = true
        isPaused = false

        startTimerService(remainingTimeInMillis)
    }

    fun stopTimer() {
        if (isTimerRunning) {
            countDownTimer?.cancel()
            isPaused = true
            isTimerRunning = false

            Log.d("TimerViewModel", "Таймер остановлен. Оставшееся время: $remainingTimeInMillis мс")
            // Останавливаем сервис
            val intent = Intent(getApplication(), TimerService::class.java)
            intent.putExtra("duration", remainingTimeInMillis) // Передаем оставшееся время
            intent.action = "STOP_TIMER"
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

        Log.d("TimerViewModel", "Запуск сервиса. Передаю в TimerService: $timeInMillis мс") // Новый лог


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }
}

