package com.example.assignment3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment3.ui.theme.Task
import com.example.assignment3.MainScreen
import com.example.assignment3.confirm.ConfirmVM
import com.example.assignment3.tasklist.TaskListView
import com.example.assignment3.tasklist.TaskListViewModel
import com.example.assignment3.ui.theme.Assignment3Theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier




class MainActivity : ComponentActivity(){
@ExperimentalComposeUiApi
@ExperimentalFoundationApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Log.d("TAG", "onCreate")
            Assignment3Theme() {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment3Theme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen()
        }
    }
}