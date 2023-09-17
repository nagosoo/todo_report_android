package com.eunji.todo_project_android.model

import android.graphics.drawable.Drawable
import java.util.Date

data class Todo(
    var id: Int?  = null,
    var plan: String? = null,
    var stamp: Drawable?  = null,
    val date: Date?  = null,
)