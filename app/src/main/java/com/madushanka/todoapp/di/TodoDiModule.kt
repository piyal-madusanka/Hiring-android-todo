package com.madushanka.todoapp.di

import android.content.Context
import androidx.room.Room
import com.madushanka.todoapp.data.local.TodoDatabase
import com.madushanka.todoapp.data.local.TodoLocalDataSource
import com.madushanka.todoapp.data.local.TodoLocalDataSourceImpl
import com.madushanka.todoapp.data.repository.TodoRepositoryImpl
import com.madushanka.todoapp.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TodoDiModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideTodoLocalDataSource(database: TodoDatabase): TodoLocalDataSource {
        return TodoLocalDataSourceImpl(database.todoDao())
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java, "planets_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository = todoRepositoryImpl
}