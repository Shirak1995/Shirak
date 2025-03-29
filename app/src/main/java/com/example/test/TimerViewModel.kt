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

    // Состояние для оставшегося времени
    var timeLeft by mutableStateOf("00:00")
        private set
    var isTimerRunning by mutableStateOf(false)
        private set
    var isPaused by mutableStateOf(false)
        private set

    fun startTimer(durationMinutes: Int) {
        if (isTimerRunning && !isPaused) return // Если таймер уже идет, ничего не делать

        if (isPaused) {
            resumeTimer()
            return
        }

        remainingTimeInMillis = (durationMinutes * 60 * 1000).toLong()
        launchTimer(remainingTimeInMillis)
    }

    private fun launchTimer(timeInMillis: Long) {
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
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
    }

    fun stopTimer() {
        if (!isTimerRunning) return
        countDownTimer?.cancel()
        isPaused = true
        isTimerRunning = false
    }

    fun resetTimer() {
        countDownTimer?.cancel()
        timeLeft = "00:00"
        remainingTimeInMillis = 0L
        isTimerRunning = false
        isPaused = false
    }

    private fun resumeTimer() {
        launchTimer(remainingTimeInMillis)
    }
}
