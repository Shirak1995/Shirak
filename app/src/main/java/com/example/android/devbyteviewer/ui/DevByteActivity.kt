package com.example.android.devbyteviewer.ui

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.devbyteviewer.R

class DevByteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val launchCount = sharedPreferences.getInt("launch_count", 0) + 1
        sharedPreferences.edit().putInt("launch_count", launchCount).apply()

        if (launchCount % 3 == 1) {
            setContentView(R.layout.activity_dev_byte_viewer)
        } else if (launchCount % 3 == 2) {
            setContentView(R.layout.activity_dev_byte_viewer_search)
        }
        else {
            setContentView(R.layout.activity_screen_3)
        }
    }
}
