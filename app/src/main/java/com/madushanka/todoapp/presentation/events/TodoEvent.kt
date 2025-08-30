package com.madushanka.todoapp.presentation.events

sealed class TodoEvent {
    data object LoadMoreTodos : TodoEvent()
    data object FetchTodos : TodoEvent()
    data object OnAddTodoClick : TodoEvent()
    data class OnDeleteTodoClick(val id: Int) : TodoEvent()
}