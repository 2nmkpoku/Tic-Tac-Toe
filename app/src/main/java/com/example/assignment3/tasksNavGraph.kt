package com.example.assignment3

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.assignment3.ui.theme.Task
import com.example.assignment3.confirm.ConfirmVM
import com.example.assignment3.newTask.NewTaskView
import com.example.assignment3.newTask.NewTaskViewModel
import com.example.assignment3.tasklist.TaskListView
import com.example.assignment3.tasklist.TaskListViewModel



@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun tasksNavGraph(
    navController: NavHostController = rememberNavController()
    ){
    val vm: TaskListViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Routes.TaskList.route
    ) {
        composable(Routes.TaskList.route) {
            TaskListScreen(vm)
        }
        composable(Routes.NewTask.route) {
            val newTaskViewModel: NewTaskViewModel = viewModel()
            NewTaskView(
                newTaskViewModel,
                onAddTask = { task ->
                    vm.addTask(task)
                    navController.navigate(Routes.TaskList.route) {
                        popUpTo(Routes.TaskList.route)
                    }
                }
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TaskListScreen(
    vm: TaskListViewModel
) {
    val confirmViewModel: ConfirmVM = viewModel()
    val tasks by vm.tasks
    val selectedSong by vm.selectedTask
    TaskListView(
        tasks,
        selectedSong,
        confirmViewModel,
        waiting=vm.waiting.value,
        waitingProgress=vm.waitingProgress.value,
        onDelete=vm::deleteTaskAsync,
        onToggle=vm::toggleDone,
        onFilter=vm::filter,
        onSelectTask=vm::selectTask
    )
}