package com.example.assignment3.ui.theme


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val contents: String,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,



)