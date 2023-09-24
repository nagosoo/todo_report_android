package com.eunji.todo_project_android.util

import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}