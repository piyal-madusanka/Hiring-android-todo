package com.madushanka.todoapp.domain.usecase

import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(): Flow<Result<List<Todo>>> =
        repository.getTodos().map { result ->
            result.fold(
                onSuccess = { todos -> Result.success(todos) },
                onFailure = { error -> Result.failure(error) }
            )
        }
}