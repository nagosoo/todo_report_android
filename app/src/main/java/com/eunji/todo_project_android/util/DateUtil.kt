package com.eunji.todo_project_android.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtil {
    val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(System.currentTimeMillis())
}