package com.madushanka.todoapp.data.local

import com.madushanka.todoapp.data.mapper.toTodo
import com.madushanka.todoapp.data.model.CachedTodo
import com.madushanka.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TodoLocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoLocalDataSource {

    override fun saveTodo(title: String, description: String) {
        todoDao.insertTodo(
            todo = CachedTodo(
                title = title,
                description = description,
                isCompleted = false
            )
        )
    }

    override fun getTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { cachedTodos ->
            cachedTodos.map { cachedTodo ->
                cachedTodo.toTodo()
            }
        }
    }
}