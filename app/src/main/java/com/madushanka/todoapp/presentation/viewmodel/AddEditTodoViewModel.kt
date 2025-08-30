package com.madushanka.todoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madushanka.todoapp.domain.usecase.AddTodosUseCase
import com.madushanka.todoapp.presentation.state.AddTodoSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val addTodosUseCase: AddTodosUseCase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _addTodoState = MutableStateFlow<AddTodoSate>(AddTodoSate.None)
    val addTodoState = _addTodoState.asStateFlow()

    fun addTodo(title: String, description: String) {
        viewModelScope.launch(coroutineDispatcher) {
            addTodosUseCase.invoke(title, description).collect { result ->
                result.fold(
                    onSuccess = {
                        _addTodoState.update {
                            AddTodoSate.Success
                        }
                    },
                    onFailure = { error ->
                        _addTodoState.update {
                            AddTodoSate.Error(error.message ?: "An unexpected error occurred")
                        }
                    }
                )
            }
        }
    }

}