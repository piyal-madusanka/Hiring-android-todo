package com.madushanka.todoapp.data.repository

import com.madushanka.todoapp.domain.repository.TodoRepository
import com.madushanka.todoapp.data.local.TodoLocalDataSource
import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.utils.LocalDataSourceException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val localDataSource: TodoLocalDataSource
) : TodoRepository {


    override suspend fun getTodos(): Flow<Result<List<Todo>>> = flow {
        try {
            localDataSource.getTodos().collect { result ->
                val cachedPlanets = result
                when {
                    cachedPlanets.isEmpty() ->
                        emit(Result.failure(Exception("No cached data available")))

                    else -> {

                        emit(Result.success(cachedPlanets))
                    }
                }
            }

        } catch (e: LocalDataSourceException) {
            emit(Result.failure(e))
        }
    }

    override suspend fun addTodo(title: String,description: String): Flow<Result<Unit>> = flow {
        try {
            localDataSource.saveTodo(title,description)
            emit(Result.success(Unit))
        } catch (e: LocalDataSourceException) {
            emit(Result.failure(Exception("failed to add todo")))
        }

    }


}