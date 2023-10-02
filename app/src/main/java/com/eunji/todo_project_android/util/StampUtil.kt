package com.eunji.todo_project_android.util

import android.graphics.drawable.Drawable
import com.eunji.todo_project_android.AppApplication.Companion.applicationContext
import com.eunji.todo_project_android.R

object StampUtil {
    val stampList: List<Drawable>

    init {
        stampList = getStampListt()
    }

    private fun getStampListt(): List<Drawable> {
        val stampDrawableList = mutableListOf<Drawable>()
        val drawableIds = applicationContext().resources.obtainTypedArray(R.array.stamp_drawables)

        for (i in 0..54) {
            drawableIds.getDrawable(i)?.let { drawable ->
                stampDrawableList.add(drawable)
            }
        }

        drawableIds.recycle()
        return stampDrawableList
    }

}