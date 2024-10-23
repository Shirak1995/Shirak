
package com.example.background

import android.app.DownloadManager
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.os.Environment

class DownloadService : Service() {

    private val binder = DownloadBinder()
    private lateinit var downloadManager: DownloadManager

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class DownloadBinder : Binder() {
        // Метод для получения ссылки на DownloadService
        fun getService(): DownloadService {
            return this@DownloadService
        }

        // Метод для начала загрузки
        fun startDownload(url: String) {
            val request = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle("Downloading File")
                setDescription("File is being downloaded...")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "downloadedfile.mp3")
            }

            downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
        }
    }

    override fun onCreate() {
        super.onCreate()
        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
