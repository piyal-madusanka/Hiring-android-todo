package com.madushanka.todoapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
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

@Composable
fun AddTodoScreen() {

    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = "Add todo"
            )
        },
        bottomBar = {
            Row (
                modifier = Modifier.padding(16.dp)
            ){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                ) { Text("Add todo") }
            }
        }) { padding ->

        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }


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

        }
    }
}


@Composable
@Preview(showBackground = true)
private fun AddTodoListScreenPreview() {
    AddTodoScreen()
}

