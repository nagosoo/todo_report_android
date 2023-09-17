package com.eunji.todo_project_android.ui.report_view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(private val spanCount: Int, private val space: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val row = position % spanCount

        /** 첫번째 column에 있는 아이템인 경우 좌측 [space] 만큼의 여백을 추가한다 */
        if (position < spanCount){
            outRect.left = space
        }
        /** 첫번째 row에 있는 아이템인 경우 상단에 [space] 만큼의 여백을 추가한다 */
        if (row == 0){
            outRect.top = space
        }
        /** 모든 아이템의 좌측과 하단에 [space] 만큼의 여백을 추가한다. */
        outRect.right = space
        outRect.bottom = space
    }

}