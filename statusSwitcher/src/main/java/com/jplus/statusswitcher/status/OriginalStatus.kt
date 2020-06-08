package com.jplus.statusswitcher.status

import android.content.Context
import android.view.View
import com.jplus.statusswitcher.base.BaseStatus

/**
 * @author JPlus
 * @date 2020/6/8.
 */
class OriginalStatus(private val mContentView: View?, private val mContext: Context):BaseStatus(mContentView, mContext) {

    override fun onCreateViewRes(): Int? {
        return null
    }

    override fun buildView(): View? {
        return null
    }
}