package com.madushanka.todoapp.presentation.componants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madushanka.todoapp.R
import com.madushanka.todoapp.domain.model.Todo
import com.madushanka.todoapp.ui.theme.CheckBoxCheck
import com.madushanka.todoapp.ui.theme.CheckBoxUnCheck
import com.madushanka.todoapp.ui.theme.dmSansTextStyle
import com.madushanka.todoapp.ui.theme.dmSansTextStyleSecond


@Composable
fun TodoItem(
    todo: Todo,
    onTodoClicked: (Todo) -> Unit,
    onTodDeleteClicked: (Int?) -> Unit,
    onTodoEditClicked: (Todo) -> Unit,
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
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Checkbox(
                        checked = todo.isCompleted,
                        onCheckedChange = { value ->
                            onCheckedChange(todo.id, value)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = CheckBoxCheck,
                            uncheckedColor = CheckBoxUnCheck
                        )
                    )
                    Text(
                        text = todo.title,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = if (todo.isCompleted)
                            dmSansTextStyleSecond
                        else
                            dmSansTextStyle,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                onTodoEditClicked(todo)
                            },
                        painter = painterResource(
                            id = R.drawable.ic_edit_icon
                        ),
                        tint = Color.Black,
                        contentDescription = null,
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                onTodDeleteClicked(todo.id)
                            },
                        painter = painterResource(
                            id = R.drawable.ic_delete
                        ),
                        tint = Color.Unspecified,
                        contentDescription = null,
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
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
        onCheckedChange = { _, _ ->

        },
        onTodDeleteClicked = {},
        onTodoEditClicked = {}
    )
}