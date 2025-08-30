package com.madushanka.todoapp.presentation.state
import com.madushanka.todoapp.domain.model.Todo

sealed class TodoState {
    data object Loading : TodoState()
    data class Success(val todos: List<Todo>) : TodoState()
    data class Error(val errorMessage: String) : TodoState()
}