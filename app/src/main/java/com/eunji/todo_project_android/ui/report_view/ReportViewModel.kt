package com.eunji.todo_project_android.ui.report_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eunji.todo_project_android.model.Rating
import com.eunji.todo_project_android.model.Todo
import com.eunji.todo_project_android.repository.TodoRepository
import com.eunji.todo_project_android.util.Const.planCnt
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            return ReportViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}

class ReportViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todoList = MutableLiveData<MutableList<Todo>>()
    val todoList: LiveData<MutableList<Todo>> = _todoList

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> = _index

    private val _stamp = MutableLiveData<MutableList<Int?>>(mutableListOf())
    val stamp: LiveData<MutableList<Int?>> = _stamp

    lateinit var rating: Rating

    fun setPlan(plan: String) {
        todoList.value?.let { list ->
            list[index.value!!].plan = plan
            _todoList.value = list
        }
    }

    fun setTodos(todoList: MutableList<Todo>) {
        _todoList.value = todoList
        todoList.map { todo ->
            _stamp.value?.add(todo.stampIndex)
        }
    }

    fun getTodos(date: String): StateFlow<List<Todo>> {
        return repository.getTodos(date)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                MutableList(planCnt) { Todo(date = date) }
            )
    }

    fun saveTodos() = viewModelScope.launch {
        for (todo in todoList.value!!) {
            repository.insertTodo(
                todo
            )
        }
    }

    fun setStamp(stampIndex: Int) {
        todoList.value?.let { list ->
            list[index.value!!].stampIndex = stampIndex
            val stampList = _stamp.value!!
            stampList[index.value!!] = stampIndex
            _stamp.value = stampList
        }
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getRating(date: String): StateFlow<Rating?> {
        return repository.getRating(date)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                Rating(date = date)
            )
    }

    fun saveRating() = viewModelScope.launch {
        repository.insertRating(
            rating
        )
    }
}