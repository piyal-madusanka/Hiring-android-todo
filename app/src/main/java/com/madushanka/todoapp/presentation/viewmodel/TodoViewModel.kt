package com.madushanka.todoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madushanka.todoapp.domain.usecase.GetTodosUseCase
import com.madushanka.todoapp.presentation.state.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onFailure
import kotlin.onSuccess

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _todoState = MutableStateFlow<TodoState>(TodoState.Loading)
    val todoState = _todoState.asStateFlow()

    init {
        fetchTodos()
    }

    fun fetchTodos() {
      viewModelScope.launch(coroutineDispatcher) {
          getTodosUseCase().collect { result ->
              result.onSuccess { todos ->
                  _todoState.update {
                      TodoState.Success(todos)
                  }

              }.onFailure { exception ->
                  _todoState.value = TodoState.Error(exception.message ?: "An unexpected error occurred")
              }
          }
      }
    }

}