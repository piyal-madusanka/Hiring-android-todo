package com.madushanka.todoapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.presentation.componants.CommonTopAppBar
import com.madushanka.todoapp.presentation.componants.TodoItem


@Composable
fun TodoListScreen(
    navigateToTodoDetails: (index: Int, todo: Todo) -> Unit
) {
    Scaffold(topBar = {
        CommonTopAppBar(
            title = "TODO List"
        )
    }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TodoList(
                todos = listOf(
                    Todo(
                        id = 1,
                        title = "New todo",
                        description = "New todo description",
                        isCompleted = false
                    ),
                    Todo(
                        id = 1,
                        title = "New todo",
                        description = "New todo description",
                        isCompleted = false
                    ),
                    Todo(
                        id = 1,
                        title = "New todo",
                        description = "New todo description",
                        isCompleted = false
                    )
                ),
                onTodoClicked = { index: Int, todo: Todo ->
                    navigateToTodoDetails(
                        index, todo
                    )
                }
            )
        }
    }
}

@Composable
fun TodoList(
    todos: List<Todo>,
    onTodoClicked: (index: Int, todo: Todo) -> Unit,
) {

    // Remembers the lazy list state to track the scroll position and load more items when needed
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        itemsIndexed(todos) { index, todo ->
            TodoItem(
                todo = todo,
                onTodoClicked = { onTodoClicked(index, todo) })
        }

    }


}


