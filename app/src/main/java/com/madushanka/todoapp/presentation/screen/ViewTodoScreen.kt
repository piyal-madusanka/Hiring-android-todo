package com.madushanka.todoapp.presentation.screen

import android.R.attr.horizontalDivider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madushanka.todoapp.presentation.componants.CommonTopAppBar
import com.madushanka.todoapp.presentation.navigation.ViewTodoScreenArgs

@Composable
fun ViewTodoScreen(
    viewTodoScreen: ViewTodoScreenArgs,
) {
    val title = remember { mutableStateOf(viewTodoScreen.title) }
    val description = remember { mutableStateOf(viewTodoScreen.description) }

    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = "View todo"
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
                    .padding(bottom = padding.calculateBottomPadding(),start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Title",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = title.value,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 16.dp,start = 16.dp, end = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = description.value,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )

            }

        }
    }
}


@Composable
@Preview(showBackground = true)
private fun AddTodoListScreenPreview() {
    ViewTodoScreen(
        viewTodoScreen = ViewTodoScreenArgs(
            title = "",
            description = "",
        )
    )
}

