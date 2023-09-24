package com.eunji.todo_project_android.repository

import com.eunji.todo_project_android.model.Todo
import com.eunji.todo_project_android.room.TodoDatabase
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TodoRepositoryImpl(
    private val todoDatabase: TodoDatabase
) : TodoRepository {
    override suspend fun insertTodo(todo: Todo) {
        todoDatabase.todoDao().insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDatabase.todoDao().deleteTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDatabase.todoDao().updateTodo(todo)
    }

    override fun getTodos(date: String): Flow<List<Todo>> {
        return todoDatabase.todoDao().getTodos(date)
    }
}