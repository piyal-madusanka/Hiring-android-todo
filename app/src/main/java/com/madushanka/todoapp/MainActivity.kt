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
import androidx.navigation.toRoute
import com.madushanka.todoapp.presentation.events.AddTodoEvent
import com.madushanka.todoapp.presentation.events.TodoEvent
import com.madushanka.todoapp.presentation.navigation.AddEditTodoScreen
import com.madushanka.todoapp.presentation.navigation.SplashScreen
import com.madushanka.todoapp.presentation.navigation.TodoListScreen
import com.madushanka.todoapp.presentation.navigation.ViewTodoScreenArgs
import com.madushanka.todoapp.presentation.screen.AddTodoScreen
import com.madushanka.todoapp.presentation.screen.SplashScreen
import com.madushanka.todoapp.presentation.screen.TodoListScreen
import com.madushanka.todoapp.presentation.screen.ViewTodoScreen
import com.madushanka.todoapp.presentation.viewmodel.AddEditTodoViewModel
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
                        onEvent = { todoEvent ->
                            when (todoEvent) {
                                is TodoEvent.OnAddTodoClick -> {
                                    navController.navigate(
                                        AddEditTodoScreen(
                                            id = null,
                                            title = "",
                                            description = "",
                                            isEdit = false
                                        )
                                    )
                                }

                                is TodoEvent.OnDeleteTodoClick -> {
                                    todoViewModel.deleteTodo(
                                        id = todoEvent.id ?: return@TodoListScreen
                                    )
                                }

                                is TodoEvent.OnTodoCheckChange -> {
                                    todoViewModel.markTodoAsCompleted(
                                        id = todoEvent.id ?: return@TodoListScreen,
                                        checked = todoEvent.check
                                    )
                                }

                                is TodoEvent.FetchTodos -> {
                                    todoViewModel.fetchTodos()
                                }

                                is TodoEvent.OnEditTodoClick -> {
                                    navController.navigate(
                                        AddEditTodoScreen(
                                            id = todoEvent.todo.id,
                                            title = todoEvent.todo.title,
                                            description = todoEvent.todo.description,
                                            isEdit = true
                                        )
                                    )
                                }

                                is TodoEvent.OnTodoClick -> {
                                    navController.navigate(
                                        ViewTodoScreenArgs(
                                            title = todoEvent.todo.title,
                                            description = todoEvent.todo.description
                                        )
                                    )
                                }
                            }
                        },
                        todoState = todoState
                    )

                }

                composable<AddEditTodoScreen> { backStackEntry ->
                    val addTodoViewModel: AddEditTodoViewModel = hiltViewModel()
                    val addTodoState by addTodoViewModel.addTodoState.collectAsStateWithLifecycle()
                    val addEditTodo: AddEditTodoScreen = backStackEntry.toRoute()

                    AddTodoScreen(
                        addTodoState = addTodoState,
                        onEvent = { todoEvent ->
                            when (todoEvent) {
                                is AddTodoEvent.AddTodo -> {
                                    addTodoViewModel.addTodo(
                                        title = todoEvent.title,
                                        description = todoEvent.description
                                    )
                                }

                                is AddTodoEvent.OnTodoAdded -> {
                                    navController.popBackStack()
                                }

                                is AddTodoEvent.EditTodo -> {
                                    addTodoViewModel.updateTodo(
                                        id = todoEvent.id ?: return@AddTodoScreen,
                                        title = todoEvent.title,
                                        description = todoEvent.description
                                    )
                                }
                            }
                        },
                        addEditTodoScreen = addEditTodo
                    )
                }

                composable<ViewTodoScreenArgs> {
                    val viewTodo: ViewTodoScreenArgs = it.toRoute()
                    ViewTodoScreen(
                        viewTodoScreen = viewTodo,
                    )
                }

            }

        }
    }

}