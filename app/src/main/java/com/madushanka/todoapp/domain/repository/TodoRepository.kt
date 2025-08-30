package com.madushanka.todoapp.domain.repository

import com.madushanka.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow


interface TodoRepository {

   suspend fun getTodos(): Flow<Result<List<Todo>>>

    suspend fun addTodo(title: String,description: String): Flow<Result<Unit>>

    suspend fun markTodoAsCompleted(id: Int, checked: Boolean): Flow<Result<Unit>>
}