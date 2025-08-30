package com.madushanka.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_todo")
data class CachedTodo(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
)
