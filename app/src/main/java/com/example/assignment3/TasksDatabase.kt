package com.example.assignment3

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.assignment3.ui.theme.Task

@Dao
interface TasksDao {
    @Query("SELECT id, name, contents, is_done FROM task")
    suspend fun getTasks(): List<Task>

    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TasksDao
}
