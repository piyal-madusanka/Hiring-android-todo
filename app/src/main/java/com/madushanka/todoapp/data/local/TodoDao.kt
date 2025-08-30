package com.madushanka.todoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madushanka.todoapp.data.model.CachedTodo
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo: CachedTodo)

    @Query("SELECT * FROM cached_todo")
    fun getAllTodos(): Flow<List<CachedTodo>>

    @Query("UPDATE cached_todo SET isCompleted = :checked WHERE id = :id")
    fun markTodoAsCompleted(id: Int,checked: Int)

    @Query("DELETE FROM cached_todo WHERE id = :id")
    fun deleteTodo(id: Int)

    @Query("UPDATE cached_todo SET title = :title, description = :description WHERE id = :id")
    fun editTodo(id: Int, title: String, description: String)
}