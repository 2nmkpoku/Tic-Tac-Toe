package com.example.assignment3.tasklist


import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment3.ITasksRepository
import com.example.assignment3.TasksDataRepository
import com.example.assignment3.ui.theme.Task
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TaskListViewModel(app: Application) : AndroidViewModel(app) {
    private val _tasks: MutableState<List<Task>> = mutableStateOf(listOf())
    val tasks: State<List<Task>> = _tasks

    private val _selected: MutableState<Task?>
    val selectedTask: State<Task?>

    private val _waiting: MutableState<Boolean>
    val waiting: State<Boolean>

    private val _waitingProgress: MutableState<Float>
    val waitingProgress: State<Float>

    //    private val _repository: ISongsRepository = SongsMemoryRepository()
    private val _repository: ITasksRepository = TasksDataRepository(app)

    init {
        viewModelScope.launch {
            _tasks.value = _repository.getTasks()
        }
        _selected = mutableStateOf(null)
        selectedTask = _selected
        _waiting = mutableStateOf(false)
        waiting = _waiting
        _waitingProgress = mutableStateOf(0.0f)
        waitingProgress = _waitingProgress
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            _waiting.value = true
            _repository.addTask(task)
            _tasks.value = _repository.getTasks()
            _waiting.value = false
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskAsync(task)
        }
    }

    suspend fun deleteTaskAsync(task: Task) {
        _waiting.value = true
        _waitingProgress.value = 0.0f
        val progJob = viewModelScope.async { _incrementProgress() }
        val deleteJob = viewModelScope.async { _repository.deleteTask(task) }
        progJob.start()
        deleteJob.await()
        progJob.cancel()
        _tasks.value = _repository.getTasks()
        _waiting.value = false
        _waitingProgress.value = 0.0f
    }

    suspend fun _incrementProgress() {
        while(true) {
            delay(500)
            _waitingProgress.value += (1.0f / 10.0f)
        }
    }

    fun toggleDone(task: Task) {
        viewModelScope.launch {
            _repository.toggleDone(task)
            _tasks.value = _repository.getTasks()
        }
    }

    fun filter(search: String) {
        viewModelScope.launch {
            _tasks.value = _repository.getTasks().filter { a -> a.name.contains(search, true) }
        }
    }

    fun selectTask(task: Task) {
        _selected.value = task
    }
}