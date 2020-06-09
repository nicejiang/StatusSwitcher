package com.jplus.statusswitcher.base

import android.content.Context
import android.view.View

/**
 * 状态基类
 * @author JPlus
 * @date 2020/6/8.
 */
abstract class BaseStatus(private val mContext: Context, private var mRootVie: View?, private val bindListener:((View?)->Unit)?) {
    /**
     * 返回布局id
     * @return 布局id
     */
    abstract fun onCreateViewRes(): Int?

    /**
     * 构建的View
     * @return 构建的View
     */
    abstract fun buildView(): View?

    /**
     * 获取根视图
     * @return 根视图
     */
    fun getRootView(): View {
        onCreateViewRes()?.let {
            if (mRootVie == null) {
                mRootVie = buildView()
            }
            if (mRootVie == null) {
                mRootVie = View.inflate(mContext, it, null)
            }
            bindListener?.let{
                it(mRootVie)
            }
        }
        return mRootVie!!
    }
}