package com.eunji.todo_project_android.model

import java.util.Date

data class Todo(
    var id: Int?,
    var plan: String?,
    var stamp: String?,
    val date: Date,
)