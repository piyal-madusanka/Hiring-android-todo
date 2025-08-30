package com.madushanka.todoapp.data.mapper
import com.madushanka.todoapp.data.model.CachedTodo
import com.madushanka.todoapp.domain.model.Todo


fun Todo.toCachedTodo(id: Int = 0): CachedTodo {
    return CachedTodo(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}

fun CachedTodo.toTodo(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}