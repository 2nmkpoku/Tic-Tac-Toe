package com.example.assignment3

import androidx.lifecycle.LiveData
import com.example.assignment3.ui.theme.Task

interface ITasksRepository {
    suspend fun getTasks(): List<Task>
    suspend fun deleteTask(song: Task)
    suspend fun addTask(song: Task)
    suspend fun toggleDone(song: Task)
}
