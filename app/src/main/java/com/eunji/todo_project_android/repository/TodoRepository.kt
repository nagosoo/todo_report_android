package com.eunji.todo_project_android.repository

import com.eunji.todo_project_android.model.Rating
import com.eunji.todo_project_android.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    fun getTodos(date:String): Flow<List<Todo>>

    suspend fun insertRating(rating: Rating)

    fun getRating(date:String): Flow<Rating?>


}