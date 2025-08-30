package com.madushanka.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madushanka.todoapp.data.model.CachedTodo


@Database(entities = [CachedTodo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}