/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.devbyteviewer.ui

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.devbyteviewer.R

/**
 * This is a single activity application that uses the Navigation library. Content is displayed
 * by Fragments.
 */
class DevByteActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val launchCount = sharedPreferences.getInt("launch_count", 0) + 1

        sharedPreferences.edit().putInt("launch_count", launchCount).apply()
        if (launchCount % 3 != 0) {
            setContentView(R.layout.activity_dev_byte_viewer)

            val searchView: SearchView = findViewById(R.id.search_view)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Здесь вы обрабатываете ввод и выполнение поиска
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Здесь вы обрабатываете ввод текста в поле поиска
                    return false
                }
            })
        } else {
            setContentView(R.layout.activity_screen_3)
        }
    }
}
