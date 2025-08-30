package com.madushanka.todoapp.presentation.events

import com.madushanka.todoapp.domain.model.Todo

sealed class TodoEvent {

    data object FetchTodos : TodoEvent()
    data object OnAddTodoClick : TodoEvent()
    data class OnDeleteTodoClick(val id: Int?) : TodoEvent()
    data class OnEditTodoClick(val todo: Todo) : TodoEvent()
    data class OnTodoClick(val todo: Todo) : TodoEvent()
    data class OnTodoCheckChange(val id: Int?, val check: Boolean) : TodoEvent()

}