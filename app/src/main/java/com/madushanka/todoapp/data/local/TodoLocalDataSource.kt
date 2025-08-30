package com.madushanka.todoapp.data.local

import com.madushanka.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow


interface TodoLocalDataSource {

    fun saveTodo(todo: Todo)

    fun getTodos(): Flow<List<Todo>>
}
