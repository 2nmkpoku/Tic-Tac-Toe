package com.example.assignment3.confirm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ConfirmDialog(
    title: String,
    text: String,
    confirmViewModel: IConfirmViewModel,
) {
    val show by confirmViewModel.showConfirmDialog
    if(show) {
        AlertDialog(
            onDismissRequest = { confirmViewModel.dismissDialog() },
            title = {
                if(!confirmViewModel.waiting.value) {
                    Text(title)
                } else {
                    Text("Deleting...")
                }
            },
            text = {
                if(confirmViewModel.waiting.value) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
//                            CircularProgressIndicator()
                        LinearProgressIndicator(progress = confirmViewModel.waitingProgress.value)
                    }
                } else {
                    Text(text)
                }
            },
            confirmButton = {
                Button({
                    confirmViewModel.onConfirmDelete()
                }, enabled = !confirmViewModel.waiting.value) {
                    Text("Delete")
                }
            }
        )
    }
}