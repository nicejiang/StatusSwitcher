package com.jplus.statusswitcher.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import com.jplus.statusswitcher.base.BaseStatus
import com.jplus.statusswitcher.status.OriginalStatus

/**
 * 状态布局管理者
 * @author JPlus
 * @date 2020/6/9.
 */
class StatusController(private val mContainer: Any, private val mBuilder: StatusSwitcher.Builder) {

    private var mLayout:StatusLayout?=null

    init {
        mLayout = when (mContainer) {
            is Activity -> {
                initStatusLayout(
                    mContainer,
                    mContainer,
                    mContainer.findViewById<View>(android.R.id.content) as ViewGroup
                )
            }
            is View -> {//view和fragment都走这里
                initStatusLayout(
                    mContainer,
                    mContainer.context,
                    if (mContainer.parent == null) null else mContainer.parent as ViewGroup
                )
            }
            else -> {
                throw IllegalArgumentException("the argument's type must be View or Activity: constructor(val mContainer)")
            }
        }
        initStatus()
    }

    private fun initStatus() {
        mBuilder.getAllStatus()?.run{
            this.forEach {
                mLayout?.addStatus(it)
            }
        }
    }

    private fun initStatusLayout(
        container: Any,
        context: Context,
        contentParent: ViewGroup?):StatusLayout{
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
        //先移除旧的视图
        contentParent?.removeView(oldContent)
        //设置StatueLayout到旧的视图位置，使用旧的视图的参数
        val layout = StatusLayout(context)
        contentParent?.addView(layout, index, oldContent?.layoutParams)
        //添加原本的状态界面
        layout.addStatus(OriginalStatus(context, oldContent, null))
       return layout
    }

    fun showStatus(statusClazz:Class<out BaseStatus>){
        mLayout?.showStatus(statusClazz)
    }

    fun showOrigin(){
        mLayout?.showStatus(OriginalStatus::class.java)
    }

    fun setStatusBindView(statusClazz:Class<out BaseStatus>, listener:(View?)->Unit){
        listener(mLayout?.getStatus(statusClazz)?.getRootView())
    }

    fun getStatusLayout():StatusLayout?{
        return mLayout
    }
}