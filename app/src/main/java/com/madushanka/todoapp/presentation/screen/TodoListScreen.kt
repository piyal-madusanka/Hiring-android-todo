package com.madushanka.todoapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madushanka.todoapp.R
import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.presentation.componants.CommonTopAppBar
import com.madushanka.todoapp.presentation.componants.ErrorMessage
import com.madushanka.todoapp.presentation.componants.LoadingIndicator
import com.madushanka.todoapp.presentation.componants.TodoItem
import com.madushanka.todoapp.presentation.events.TodoEvent
import com.madushanka.todoapp.presentation.state.TodoState


@Composable
fun TodoListScreen(
    todoState: TodoState,
    onEvent: (TodoEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = "tasked"
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .wrapContentHeight()
                    .padding(bottom = 50.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 16.dp)
                        .clickable {
                            onEvent(TodoEvent.OnAddTodoClick)
                        },
                    painter = painterResource(
                        id = R.drawable.ic_add_task
                    ),
                    tint = Color.Unspecified,
                    contentDescription = null,
                )
            }
        }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding.calculateBottomPadding())
        ) {
            when (todoState) {
                is TodoState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
                is TodoState.Success -> TodoList(
                    todos = (todoState).todos,
                    onEvent = onEvent
                )

                is TodoState.Error -> ErrorMessage(
                    message = (todoState).errorMessage, onEvent = onEvent
                )
            }

        }
    }
}

@Composable
fun TodoList(
    todos: List<Todo>,
    onEvent: (TodoEvent) -> Unit,
) {

    // Remembers the lazy list state to track the scroll position and load more items when needed
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        itemsIndexed(todos) { index, todo ->
            TodoItem(
                todo = todo,
                onCheckedChange = { id, isChecked ->
                    onEvent(TodoEvent.OnTodoCheckChange(id, isChecked))
                },
                onTodoClicked = {
                    onEvent(TodoEvent.OnTodoClick(todo))
                },
                onTodDeleteClicked = { id ->
                    onEvent(TodoEvent.OnDeleteTodoClick(id))
                },
                onTodoEditClicked = {
                    onEvent(TodoEvent.OnEditTodoClick(todo))
                }
            )
        }

    }

}


@Composable
@Preview(showBackground = true)
private fun TodoListScreenScreenPreview() {
    TodoListScreen(
        todoState = TodoState.Success(
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
            )
        ),
        onEvent = {}
    )
}
