package com.eunji.todo_project_android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int?  = null,
    var plan: String? = null,
    var stampIndex: Int?  = null,
    val date: String?  = null,
)