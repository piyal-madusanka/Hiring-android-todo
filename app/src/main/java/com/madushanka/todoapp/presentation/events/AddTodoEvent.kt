package com.madushanka.todoapp.presentation.events

sealed class AddTodoEvent {
    data object OnTodoAdded : AddTodoEvent()
    data class AddTodo(val title: String, val description: String) : AddTodoEvent()
    data class EditTodo(val id: Int?, val title: String, val description: String) : AddTodoEvent()
}