package com.jplus.statusswitcher.base

import android.content.Context
import android.view.View

/**
 * @author JPlus
 * @date 2020/6/8.
 */
abstract class BaseStatus(private var mRootVie: View?, private val mContext: Context) {

    abstract fun onCreateViewRes(): Int?

    abstract fun buildView(): View?

    fun getRootView(): View {
        onCreateViewRes()?.let {
            if (mRootVie == null) {
                mRootVie = buildView()
            }
            if (mRootVie == null) {
                mRootVie = View.inflate(mContext, it, null)
            }
        }
        return mRootVie!!
    }

}