package com.example.assignment3

sealed class Routes(val route: String) {
    object TaskList : Routes("tasklist")
    object NewTask : Routes("newtask")
}