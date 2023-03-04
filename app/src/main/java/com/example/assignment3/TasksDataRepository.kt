package com.example.assignment3

import android.app.Application
import androidx.room.Room
import com.example.assignment3.ui.theme.Task
import com.example.assignment3.ITasksRepository
import com.example.assignment3.TasksDatabase

class TasksDataRepository(app: Application) : ITasksRepository {
    private val db: TasksDatabase = Room.databaseBuilder(
        app,
        TasksDatabase::class.java,
        "songs.db"
    ).fallbackToDestructiveMigration().build()

 override suspend fun getTasks(): List<Task> {
        return db.taskDao().getTasks()
    }

 override suspend fun deleteTask(task: Task) {
        db.taskDao().deleteTask(task)
    }

 override suspend fun addTask(task: Task) {
        db.taskDao().addTask(task)
    }

override suspend fun toggleDone(task: Task) {
        val newTask = task.copy(isDone = !task.isDone)
        db.taskDao().updateTask(newTask)
    }
}

// override before each suspend