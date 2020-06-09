package com.jplus.status

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jplus.status.status.NoNetStatus
import com.jplus.statusswitcher.core.StatusSwitcher
import com.jplus.statusswitcher.status.OriginalStatus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mFragment: TestFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val layout = StatusSwitcher().register(this).apply {
//                this.addStatus(NoNetStatus(this@MainActivity))
//        }
//        layout.showStatusView(OriginalStatus::class.java)
//
//        button_test?.setOnClickListener {
//            layout.showStatusView(NoNetStatus::class.java)
//        }
        if (mFragment == null) {
            mFragment = TestFragment()
        }
        mFragment?.let {
            supportFragmentManager.beginTransaction().add(R.id.fly_content, it, "test").commit()
        }
    }

}