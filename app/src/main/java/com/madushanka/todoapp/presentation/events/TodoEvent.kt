package com.madushanka.todoapp.presentation.events

sealed class TodoEvent {

    data object FetchTodos : TodoEvent()
    data object OnAddTodoClick : TodoEvent()
    data class OnDeleteTodoClick(val id: Int) : TodoEvent()
    data class OnTodoCheckChange(val id: Int?,val check: Boolean) : TodoEvent()
}