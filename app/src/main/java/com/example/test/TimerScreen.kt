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
    var isInputValid by remember { mutableStateOf(true) } // Состояние для проверки корректности ввода
    var errorMessage by remember { mutableStateOf("") } // Сообщение об ошибке

    // Проверка на корректность ввода
    fun validateInput(input: String): Boolean {
        return input.toIntOrNull() != null && input.toInt() > 0
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = time,
            onValueChange = {
                time = it
                // При изменении ввода проверяем его корректность
                if (validateInput(it)) {
                    isInputValid = true
                    errorMessage = ""
                } else {
                    isInputValid = false
                    errorMessage = "Введите положительное целое число"
                }
            },
            label = { Text("Минуты") },
            isError = !isInputValid // Показываем ошибку, если ввод некорректный
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Отображение оставшегося времени
        Text(
            text = "Оставшееся время: ${timerViewModel.timeLeft}",
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Если есть сообщение об ошибке, отображаем его
        if (!isInputValid) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки управления
        Row {
            Button(
                onClick = {
                    if (isInputValid) {
                        timerViewModel.startTimer(time.toInt())
                    }
                },
                enabled = isInputValid // Блокируем кнопку, если ввод некорректен
            ) {
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
