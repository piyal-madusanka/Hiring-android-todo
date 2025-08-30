package com.madushanka.todoapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madushanka.todoapp.presentation.componants.CommonTopAppBar
import com.madushanka.todoapp.presentation.componants.CustomEditText
import com.madushanka.todoapp.presentation.componants.ErrorMessage
import com.madushanka.todoapp.presentation.componants.LoadingIndicator
import com.madushanka.todoapp.presentation.events.AddTodoEvent
import com.madushanka.todoapp.presentation.events.AddTodoEvent.AddTodo
import com.madushanka.todoapp.presentation.events.AddTodoEvent.OnTodoAdded
import com.madushanka.todoapp.presentation.navigation.AddEditTodoScreen
import com.madushanka.todoapp.presentation.state.AddTodoSate

@Composable
fun AddTodoScreen(
    addEditTodoScreen: AddEditTodoScreen,
    addTodoState: AddTodoSate,
    onEvent: (AddTodoEvent) -> Unit,
) {
    val id = remember { mutableStateOf(addEditTodoScreen.id) }
    val title = remember { mutableStateOf(addEditTodoScreen.title) }
    val description = remember { mutableStateOf(addEditTodoScreen.description) }

    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = if (addEditTodoScreen.isEdit) "Update todo" else "Add todo"
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = padding.calculateBottomPadding()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                CustomEditText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = title,
                    labelText = "add title",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                )

                CustomEditText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    text = description,
                    singleLine = false,
                    labelText = "add Description",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                )
                Row(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 20.dp,
                        bottom = 50.dp
                    )
                )
                {
                    Button(
                        enabled = title.value.isNotEmpty() && title.value.isNotBlank() && description.value.isNotEmpty() && description.value.isNotBlank() && addTodoState != AddTodoSate.Loading,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (title.value.isNotEmpty() && title.value.isNotBlank() && description.value.isNotEmpty() && description.value.isNotBlank()) {
                                if (addEditTodoScreen.isEdit) {
                                    onEvent(
                                        AddTodoEvent.EditTodo(
                                            id = id.value,
                                            title = title.value,
                                            description = description.value
                                        )
                                    )
                                } else {
                                    onEvent(
                                        AddTodo(
                                            title = title.value,
                                            description = description.value
                                        )
                                    )
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.DarkGray,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                    ) {
                        Text(
                            text = if (addEditTodoScreen.isEdit) "Update Todo" else "Add todo",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                    }
                }
            }
            when (addTodoState) {
                is AddTodoSate.Error -> ErrorMessage(
                    message = (addTodoState).errorMessage, onEvent = {
                        if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                            onEvent(
                                AddTodo(
                                    title = title.value,
                                    description = description.value
                                )
                            )
                        }
                    }
                )

                AddTodoSate.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
                AddTodoSate.Success -> {
                    onEvent(OnTodoAdded)
                }

                AddTodoSate.None -> {
                    // Do nothing
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun AddTodoListScreenPreview() {
    AddTodoScreen(
        addTodoState = AddTodoSate.Loading,
        onEvent = {},
        addEditTodoScreen = AddEditTodoScreen(
            id = null,
            title = "",
            description = "",
            isEdit = false
        )
    )
}

