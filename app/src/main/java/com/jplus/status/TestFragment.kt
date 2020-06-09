package com.jplus.status

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jplus.status.status.NoNetStatus
import com.jplus.statusswitcher.core.StatusController
import com.jplus.statusswitcher.core.StatusLayout
import com.jplus.statusswitcher.core.StatusSwitcher
import com.jplus.statusswitcher.status.OriginalStatus
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author JPlus
 * @date 2020/6/8.
 */
class TestFragment:Fragment() {
    private val testList = mutableListOf<String>()

    var mController:StatusController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for(i:Int in 0..10){
            testList.add("$i")
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mController?.showOrigin()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("pipa", "$root")
        var switcher:StatusSwitcher?=null
        activity?.let{
            switcher = StatusSwitcher.Builder()
                .addStatus(NoNetStatus(it) { view->
                    view?.findViewById<Button>(R.id.btn_not_net_test)?.setOnClickListener {
                        mController?.showOrigin()
                    }
                })
                .build()
        }
        mController = switcher?.register(root)
        root.findViewById<Button>(R.id.btn_fragment_test)?.setOnClickListener {
            root.findViewById<TextView>(R.id.tv_test_data)?.text = "0987654321"
            mController?.showStatus(NoNetStatus::class.java)
        }
        root.findViewById<TextView>(R.id.tv_test_data)?.text = "1234567890"
        return mController?.getStatusLayout()
    }
}