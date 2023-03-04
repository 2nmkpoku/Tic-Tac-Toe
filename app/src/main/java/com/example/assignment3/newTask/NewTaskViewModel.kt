package com.example.assignment3.newTask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.assignment3.ui.theme.Task

class NewTaskViewModel : ViewModel() {
    private val _name: MutableState<String> = mutableStateOf("")
    val name: State<String> = _name
    private val _contents: MutableState<String> = mutableStateOf("")
    val contents: State<String> = _contents
    private val _isDone: MutableState<Boolean> = mutableStateOf(false)
    val isDone: State<Boolean> = _isDone

    fun setName(name: String) {
        _name.value = name
    }

    fun setContents(contents: String) {
        _contents.value = contents
    }



    fun setDone(isDone: Boolean) {
        _isDone.value = isDone
    }

    /**
     * Validate the input fields and will throw an exception if not valid.
     * Otherwise, a valid Song object is returned
     */
    fun validate(): Task {
        if(name.value.isEmpty()) {
            throw Exception("Task name needed")
        }
        if(contents.value.isEmpty()) {
            throw Exception("No contents entered")
        }


        //val trackInt = track.value.toInt()
        return Task(0, name.value, contents.value, isDone.value)


    }
}