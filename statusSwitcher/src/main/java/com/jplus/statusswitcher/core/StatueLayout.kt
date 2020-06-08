package com.jplus.statusswitcher.core

import android.content.Context
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children
import com.jplus.statusswitcher.base.BaseStatus

/**
 * @author JPlus
 * @date 2020/4/17.
 */
class StatueLayout(context: Context) : FrameLayout(context) {
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
   fun showStatusView(status:Class<out BaseStatus>){
       if(status==mCurrentStatus){
           return
       }
       if(childCount>1){
           removeViewAt(1)
       }
       Log.d("pipa", "mStatusMap:${mStatusMap}")
       mStatusMap?.let {
           it[mCurrentStatus]?.getRootView()?.visibility = View.GONE
           if(it.keys.contains(status)){
               addView(it[status]?.getRootView())
               mCurrentStatus = status
           }
       }
   }
}