package com.jplus.status.status

import android.content.Context
import android.view.View
import com.jplus.status.R
import com.jplus.statusswitcher.base.BaseStatus

/**
 * @author JPlus
 * @date 2020/6/8.
 */
class NoNetStatus(private val mContext:Context, private var bindListener:((View?)->Unit)?):BaseStatus( mContext, null, bindListener) {
    override fun onCreateViewRes(): Int? {
        return R.layout.layout_not_net
    }

    override fun buildView(): View? {
        return null
    }
}