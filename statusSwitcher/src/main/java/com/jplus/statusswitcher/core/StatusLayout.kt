package com.jplus.statusswitcher.core

import android.content.Context
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.jplus.statusswitcher.base.BaseStatus
import com.jplus.statusswitcher.status.OriginalStatus
import java.lang.RuntimeException

/**
 * 状态布局
 * @author JPlus
 * @date 2020/4/17.
 */
class StatusLayout(context: Context) : FrameLayout(context) {
    private var mStatusMap:HashMap<Class<out BaseStatus>, BaseStatus>?=null
    private var mCurrentStatus:Class<out BaseStatus>?=null

    init {
        mStatusMap = hashMapOf()
    }
    private fun isMainThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }

    fun addStatus(status:BaseStatus){
        mStatusMap?.let {
            if(!it.keys.contains(status::class.java)){
                it[status::class.java] = status
            }
        }
    }

    fun getStatus(statusClazz:Class<out BaseStatus>):BaseStatus?{
        if(mStatusMap?.containsKey(statusClazz)==false){
            throw RuntimeException("not found this status in list of status.")
        }
        return mStatusMap?.get(statusClazz)
    }
   fun showStatus(statusClazz:Class<out BaseStatus>){
       if(statusClazz==mCurrentStatus){
           return
       }
       if(childCount>1){
           removeViewAt(1)
       }
       mStatusMap?.let {
           removeView(it[mCurrentStatus]?.getRootView())
           if(it.keys.contains(statusClazz)){
               addView(it[statusClazz]?.getRootView())
               mCurrentStatus = statusClazz
           }
       }
   }
}