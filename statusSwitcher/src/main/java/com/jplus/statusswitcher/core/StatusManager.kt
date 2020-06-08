package com.jplus.statusswitcher.core

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import com.jplus.statusswitcher.status.OriginalStatus

/**
 * @author JPlus
 * @date 2020/6/8.
 */
class StatusManager {

    fun register(container: Any):StatueLayout {
        return when (container) {
            is Activity -> {
                setStatusView(
                    container,
                    container,
                    container.findViewById<View>(android.R.id.content) as ViewGroup
                )
            }
            is View -> {
                setStatusView(container, container.context, if(container.parent==null) null else container.parent as ViewGroup)
            }
            else -> {
                throw IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)")
            }
        }
    }

    private fun setStatusView(container: Any, context: Context, contentParent: ViewGroup?):StatueLayout {
        var index = 0
        val oldContent = if (container is View) {
            contentParent?.forEach {
                if (it === container) return@forEach
                index++
            }
            container
        } else {
            contentParent?.getChildAt(0)
        }
        Log.d("pipa", "container:$container, contentParent:$contentParent")
        //先移除旧的视图
        contentParent?.removeView(oldContent)
        //设置StatueLayout到旧的视图位置，使用旧的视图的参数
        val layout = StatueLayout(context)
        contentParent?.addView(layout, index, oldContent?.layoutParams)
        layout.addStatus(OriginalStatus(oldContent, context))
        return layout
    }
}