package com.jplus.status

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jplus.status.status.NoNetStatus
import com.jplus.statusswitcher.core.StatueLayout
import com.jplus.statusswitcher.core.StatusManager
import com.jplus.statusswitcher.status.OriginalStatus

/**
 * @author JPlus
 * @date 2020/6/8.
 */
class TestFragment:Fragment() {
    private var layout: StatueLayout?=null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.activity?.runOnUiThread {
            layout?.showStatusView(OriginalStatus::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("pipa", "$root")
        layout =  StatusManager().register(root).apply {
            activity?.let{
                this.addStatus(NoNetStatus(it))
            }
        }
        root.findViewById<Button>(R.id.btn_fragment_test)?.setOnClickListener {
            this.activity?.runOnUiThread {
                layout?.showStatusView(NoNetStatus::class.java)
            }
        }
        return layout
    }
}