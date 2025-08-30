package com.madushanka.todoapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madushanka.todoapp.presentation.componants.CommonTopAppBar
import com.madushanka.todoapp.presentation.componants.CustomEditText
import com.madushanka.todoapp.presentation.componants.ErrorMessage
import com.madushanka.todoapp.presentation.componants.LoadingIndicator
import com.madushanka.todoapp.presentation.events.AddTodoEvent
import com.madushanka.todoapp.presentation.events.AddTodoEvent.*
import com.madushanka.todoapp.presentation.state.AddTodoSate

@Composable
fun AddTodoScreen(
    addTodoState: AddTodoSate,
    onEvent: (AddTodoEvent) -> Unit,
) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = "Add todo"
            )
        }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
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
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (title.value.isNotEmpty() && title.value.isNotBlank() && description.value.isNotEmpty() && description.value.isNotBlank()) {
                                onEvent(
                                    AddTodo(
                                        title = title.value,
                                        description = description.value
                                    )
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                    ) { Text("Add todo") }
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
        onEvent = {}
    )
}

