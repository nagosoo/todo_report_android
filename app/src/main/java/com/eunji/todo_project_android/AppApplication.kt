package com.eunji.todo_project_android

import android.app.Application
import android.content.Context

class AppApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: AppApplication
        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }
}