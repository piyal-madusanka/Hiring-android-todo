package com.madushanka.todoapp.presentation.componants

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.ui.theme.CheckBoxCheck
import com.madushanka.todoapp.ui.theme.CheckBoxUnCheck
import com.madushanka.todoapp.ui.theme.dmSansTextStyle


@Composable
fun TodoItem(
    todo: Todo,
    onTodoClicked: (Todo) -> Unit,
    onCheckedChange: (Int?, Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTodoClicked(todo) },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = White, contentColor = White
        ),
    ) {
        Row(
            modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = {value->
                    onCheckedChange(todo.id,value)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = CheckBoxCheck,
                    uncheckedColor = CheckBoxUnCheck
                )
            )
            Text(
                text = todo.title,
                color = androidx.compose.ui.graphics.Color.Black,
                style = dmSansTextStyle,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlanetItem() {
    TodoItem(
        todo = Todo(
            id = 1,
            title = "Sample Todo",
            description = "this is todo Item",
            isCompleted = false
        ), onTodoClicked = {},
        onCheckedChange = {_,_->

        }
    )
}