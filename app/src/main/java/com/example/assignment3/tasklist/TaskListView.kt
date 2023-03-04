package com.example.assignment3.tasklist


import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.assignment3.ui.theme.Task

import com.example.assignment3.AddTaskFAB
import com.example.assignment3.confirm.ConfirmVM
//import edu.towson.cosc435.valis.labsapp.ui.LandscapeView
//import edu.towson.cosc435.valis.labsapp.ui.SearchBar
import com.example.assignment3.TaskRow
import com.example.assignment3.confirm.*

@ExperimentalFoundationApi
@Composable
fun TaskListView(
    tasks: List<Task>,
    selectedTask: Task?,
    confirmViewModel: ConfirmVM,
    waiting: Boolean,
    waitingProgress: Float,
    onDelete: suspend (Task) -> Unit,
    onToggle: (Task) -> Unit,
    onFilter: (String) -> Unit,
    onSelectTask: (Task) -> Unit,
) {
    confirmViewModel.waiting.value = waiting
    confirmViewModel.waitingProgress.value = waitingProgress
    Box(
        contentAlignment = Alignment.Center,
    ) {
        ConfirmDialog(
            title = "Confirm",
            text = "Are you sure you want to delete?",
            confirmViewModel = confirmViewModel,
        )
        Column(
            modifier = Modifier
                .alpha(if(waiting) 0.2f else 1.0f)
        ) {
            //SearchBar(onFilter = onFilter)
            val config = LocalConfiguration.current
            if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LazyColumn {
                    itemsIndexed(tasks) { idx, task ->
                        TaskRow(task, { idx ->
                            confirmViewModel.showConfirmDelete(onConfirm={ onDelete(task) })
                        }, onToggle, onSelectTask)
                    }
                }
            }
        }
        if(waiting) {
            CircularProgressIndicator()
        }
    }
}