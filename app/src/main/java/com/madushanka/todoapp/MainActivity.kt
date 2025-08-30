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
import com.madushanka.rehersal.presentation.screen.TodoListScreen
import com.madushanka.todoapp.presentation.navigation.SplashScreen
import com.madushanka.todoapp.presentation.navigation.TodoListScreen
import com.madushanka.todoapp.presentation.screen.SplashScreen
import com.madushanka.todoapp.presentation.viewmodel.TodoViewModel
import com.madushanka.todoapp.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
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

                            },
                            todoState = todoState
                        )

                    }
                }
            }
        }
    }

}