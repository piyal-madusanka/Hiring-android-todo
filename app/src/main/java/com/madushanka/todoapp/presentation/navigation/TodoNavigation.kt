package com.madushanka.todoapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object TodoListScreen


@Serializable
data class AddEditTodoScreen(
    val id: Int? = null,
    val title: String,
    val description: String,
    val isEdit: Boolean = false
)