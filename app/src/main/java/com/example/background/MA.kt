
package com.example.background

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MA : ComponentActivity() {

    private var isBound = false
    private lateinit var downloadService: DownloadService
    private lateinit var downloadBinder: DownloadService.DownloadBinder // объявляем переменную для биндерa

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as DownloadService.DownloadBinder // получаем биндер
            downloadService = downloadBinder.getService() // получаем экземпляр DownloadService
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Привязываем сервис
        Intent(this, DownloadService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        // Устанавливаем контент
        setContent {
            SurfaceContent()
        }
    }

    @Composable
    fun SurfaceContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { downloadFile("https://www.example.com/somefile.mp3") }) {
                Text("Download MP3")
            }
        }
    }

    private fun downloadFile(url: String) {
        if (isBound) {
            downloadBinder.startDownload(url) // Вызываем метод через downloadBinder
            Toast.makeText(this, "Download started!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Service is not bound", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}
