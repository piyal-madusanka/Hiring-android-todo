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
}