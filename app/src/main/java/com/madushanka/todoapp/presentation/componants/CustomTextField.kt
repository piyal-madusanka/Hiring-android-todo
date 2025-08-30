package com.madushanka.todoapp.presentation.componants

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomEditText(
    text: MutableState<String>,
    placeholder: String = "",
    labelText: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    borderThickness: Dp = 1.dp,
    borderCorner: Dp = 8.dp,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardActions: KeyboardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Next)
        },
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = keyboardType, imeAction = imeAction
    )
) {
    var searchText by remember { text }

    val interactionSource = remember { MutableInteractionSource() }
    var focus by remember {
        mutableStateOf(Color.Gray)
    }

    TextField(
        value = searchText,
        placeholder = {
            Text(text = placeholder,color = Color.Gray)
        },
        onValueChange = {
            searchText = it
            onValueChange(searchText)
        },
        interactionSource = interactionSource,
        modifier = modifier
            .background(Color.Transparent)
            .onFocusChanged {
                focus = if (it.isFocused) Color.Black
                else Color.Gray
            }
            .border(
                width = borderThickness, color = focus, shape = RoundedCornerShape(borderCorner)
            ),
        label = {
            Text(
                text = labelText,
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF8C8C8C),
                ), modifier = Modifier.padding(vertical = 4.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        ),
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else 4,
        enabled = enabled,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions

    )
}