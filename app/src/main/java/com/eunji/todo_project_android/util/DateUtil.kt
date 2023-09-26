package com.eunji.todo_project_android.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtil {

    private val calendar = Calendar.getInstance()

    fun getTodayDate(year: Int, month: Int, dayOfMonth: Int): String {
        calendar.set(year, month, dayOfMonth)
        val dayOfWeek = SimpleDateFormat("E", Locale.KOREAN).format(calendar.time)

        return "$year.${String.format("%02d", month + 1)}.$dayOfMonth($dayOfWeek)"
    }

    val todayDate = getTodayDate(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}