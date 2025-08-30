package com.madushanka.todoapp.data.local

import com.madushanka.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow


interface TodoLocalDataSource {

    fun saveTodo(title: String,description: String)

    fun getTodos(): Flow<List<Todo>>

    fun markTodoAsCompleted(id: Int, checked: Boolean)
}
