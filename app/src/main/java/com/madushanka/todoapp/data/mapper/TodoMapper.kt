package com.madushanka.todoapp.data.mapper
import com.madushanka.todoapp.data.model.CachedTodo
import com.madushanka.todoapp.domain.model.Todo


fun CachedTodo.toTodo(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}