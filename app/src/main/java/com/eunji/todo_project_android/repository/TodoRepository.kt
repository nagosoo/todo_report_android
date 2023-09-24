package com.eunji.todo_project_android.repository

import com.eunji.todo_project_android.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(book: Todo)

    suspend fun deleteTodo(book: Todo)

    suspend fun updateTodo(book: Todo)

    fun getTodos(date:String): Flow<List<Todo>>

}