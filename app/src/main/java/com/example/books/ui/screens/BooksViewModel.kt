package com.example.books.ui.screens

import com.example.books.BooksApplication
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.books.data.BooksRepository
import com.example.books.network.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BooksUiState {
    data class Success(val photos: List<Book>) : BooksUiState
    object Error : BooksUiState
    object Loading : BooksUiState
}

class BooksViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set


    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            try {
                val result = booksRepository.getBooks().items
                booksUiState = BooksUiState.Success(result)
                // Или без переменной result, то есть вот так:
                // marsUiState = MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                booksUiState = BooksUiState.Error
            } catch (e: HttpException) {
                booksUiState = BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}
