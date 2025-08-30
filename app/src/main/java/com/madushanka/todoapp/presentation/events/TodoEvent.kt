package com.madushanka.todoapp.presentation.events

sealed class TodoEvent {
    data object LoadMoreTodos : TodoEvent()
    data object FetchTodos : TodoEvent()
}