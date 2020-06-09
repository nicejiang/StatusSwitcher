package com.jplus.statusswitcher.status

import android.content.Context
import android.view.View
import com.jplus.statusswitcher.base.BaseStatus

/**
 * 原始布局
 * @author JPlus
 * @date 2020/6/8.
 */
class OriginalStatus (private val mContext: Context,private val mContentView: View?, private var bindListener:((View?)->Unit)?):BaseStatus(mContext, mContentView, bindListener) {

    override fun onCreateViewRes(): Int? {
        return null
    }

    override fun buildView(): View? {
        return null
    }



}