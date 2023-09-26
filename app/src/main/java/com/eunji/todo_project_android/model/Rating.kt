package com.eunji.todo_project_android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating (
    @PrimaryKey
    var date: String,
    var rating: String? = null,
)