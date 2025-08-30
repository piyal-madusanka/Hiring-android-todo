package com.madushanka.todoapp.presentation.state

sealed class AddTodoSate {
    data object Loading : AddTodoSate()
    data object None : AddTodoSate()
    data object Success : AddTodoSate()
    data class Error(val errorMessage: String) : AddTodoSate()
}