package com.example.test.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.test.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerService : Service() {

    private var timerJob: Job? = null
    private var remainingTime: Long = 0L
    private var isTimerRunning: Boolean = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        if (action == null || action != "STOP_TIMER") {
            val duration = intent?.getLongExtra("duration", 0L) ?: 0L

        startForeground(1, createNotification("Таймер запущен"))
        startTimer(duration)
        } else {
            stopTimer()
        }
        return START_STICKY
    }

    private fun startTimer(durationMillis: Long) {
        if (isTimerRunning) return

        remainingTime = durationMillis
        isTimerRunning = true

        timerJob?.cancel()

        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (remainingTime > 0 && isTimerRunning) {
                Log.d("TimerService", "Осталось ${remainingTime / 60000} минут")
                val minutesLeft = remainingTime / 60000
                val secondsLeft = (remainingTime % 60000) / 1000
                if (minutesLeft >= 1) {
                    sendNotification("Осталось $minutesLeft минут $secondsLeft секунд")
                } else {
                    sendNotification("Осталось $secondsLeft секунд")
                }
                delay(60_000)
                remainingTime -= 60_000
            }
            Log.d("TimerService", "Таймер завершён!")
            if (isTimerRunning) {
                sendNotification("Таймер завершён!")
            }
            delay(1500)
            stopSelf()
        }
    }

    private fun sendNotification(message: String) {
        val notification = NotificationCompat.Builder(this, "timer_channel")
            .setContentTitle("Таймер")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()

        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(1, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "timer_channel",
                "Таймер",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(text: String): Notification {
        return NotificationCompat.Builder(this, "timer_channel")
            .setContentTitle("Таймер")
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
    }

    override fun onDestroy() {
        timerJob?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun stopTimer() {
        isTimerRunning = false
        timerJob?.cancel()
        stopForeground(true)
        stopSelf()
    }
}