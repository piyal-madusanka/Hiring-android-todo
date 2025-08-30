package com.madushanka.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madushanka.todoapp.presentation.events.TodoEvent
import com.madushanka.todoapp.presentation.navigation.AddEditTodoScreen
import com.madushanka.todoapp.presentation.navigation.SplashScreen
import com.madushanka.todoapp.presentation.navigation.TodoListScreen
import com.madushanka.todoapp.presentation.screen.AddTodoScreen
import com.madushanka.todoapp.presentation.screen.SplashScreen
import com.madushanka.todoapp.presentation.screen.TodoListScreen
import com.madushanka.todoapp.presentation.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = SplashScreen
            ) {

                composable<SplashScreen> {
                    SplashScreen(onNavigateToToDoListScreen = {
                        navController.navigate(TodoListScreen) {
                            popUpTo(SplashScreen) { inclusive = true }
                        }
                    })
                }
                composable<TodoListScreen> {

                    val todoViewModel: TodoViewModel = hiltViewModel()
                    val todoState by todoViewModel.todoState.collectAsStateWithLifecycle()

                    TodoListScreen(
                        navigateToTodoDetails = { index, todo -> },
                        onEvent = { todoEvent ->
                            when (todoEvent) {
                                is TodoEvent.OnAddTodoClick -> {
                                    navController.navigate(AddEditTodoScreen(id = ""))
                                }

                                TodoEvent.FetchTodos -> TODO()
                                TodoEvent.LoadMoreTodos -> TODO()
                                is TodoEvent.OnDeleteTodoClick -> TODO()
                            }
                        },
                        todoState = todoState
                    )

                }

                composable<AddEditTodoScreen> {
                    AddTodoScreen()
                }


            }

        }
    }

}