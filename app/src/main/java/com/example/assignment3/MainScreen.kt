package com.example.assignment3

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.Routes
import com.example.assignment3.tasksNavGraph

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(){
    val nav = rememberNavController()
    Scaffold(       topBar = {
        TopBar()
    },
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
        tasksNavGraph(nav)
    }

}

@Composable
private fun TopBar() {
    TopAppBar(
        title = { Text("Songs App") },
    )
}

@Composable
private fun BottomBar(
    nav: NavHostController
) {
    val backStateEntry = nav.currentBackStackEntryAsState()
    val currentDestination = backStateEntry.value?.destination
    BottomNavigation(
        elevation = 16.dp
    ) {
        BottomNavigationItem(
            selected = currentDestination?.route == Routes.TaskList.route,
            onClick = {
                nav.navigate(Routes.TaskList.route) {
                    popUpTo(Routes.TaskList.route)
                }
            },
            icon = {
                Icon(Icons.Default.Home, "")
            },
            label = {
                Text("SongList")
            }
        )
        BottomNavigationItem(
            selected = currentDestination?.route == Routes.NewTask.route,
            onClick = {
                nav.navigate(Routes.NewTask.route) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(Icons.Default.Add, "")
            },
            label = {
                Text("New Task")
            }
        )
    }
}