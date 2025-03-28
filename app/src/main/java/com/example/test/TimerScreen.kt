package com.example.test

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.TimerViewModel

@Composable
fun TimerScreen(timerViewModel: TimerViewModel = viewModel()) {
    var time by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Минуты") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Отображение оставшегося времени
        Text(
            text = "Оставшееся время: ${timerViewModel.timeLeft}",
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки управления
        Row {
            Button(onClick = { timerViewModel.startTimer(time.toIntOrNull() ?: 1) }) {
                Text("Старт")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { timerViewModel.stopTimer() }) {
                Text("Стоп")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { timerViewModel.resetTimer() }) {
                Text("Сброс")
            }
        }
    }
}