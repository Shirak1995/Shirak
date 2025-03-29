package com.example.test.ui.screens

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.ui.TimerViewModel

@Composable
fun TimerScreen(timerViewModel: TimerViewModel = viewModel()) {
    var time by remember { mutableStateOf("1") }
    var isInputValid by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

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
                if (validateInput(it)) {
                    isInputValid = true
                    errorMessage = ""
                } else {
                    isInputValid = false
                    errorMessage = "Введите положительное целое число"
                }
            },
            label = { Text("Минуты") },
            isError = !isInputValid
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Оставшееся время: ${timerViewModel.timeLeft}",
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!isInputValid) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    if (isInputValid) {
                        timerViewModel.startTimer(time.toInt())
                    }
                },
                enabled = isInputValid
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