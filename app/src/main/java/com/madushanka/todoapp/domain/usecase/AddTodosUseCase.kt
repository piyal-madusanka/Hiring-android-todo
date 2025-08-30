package com.madushanka.todoapp.domain.usecase

import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(title: String,description: String): Flow<Result<Unit>> =
        repository.addTodo(title,description).map { result ->
            result.fold(
                onSuccess = { Result.success(Unit) },
                onFailure = { error -> Result.failure(error) }
            )
        }

}