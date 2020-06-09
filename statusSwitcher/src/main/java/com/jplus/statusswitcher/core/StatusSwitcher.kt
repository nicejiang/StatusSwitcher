package com.jplus.statusswitcher.core

import com.jplus.statusswitcher.base.BaseStatus

/**
 * 状态切换器
 * @author JPlus
 * @date 2020/6/8.
 */
open class StatusSwitcher {
    private var mBuilder: Builder? = null

    fun register(container: Any):StatusController {
        return StatusController(container, mBuilder?:Builder())
    }


    fun build(builder: Builder) {
        mBuilder = builder
    }

    class Builder {
        private var mStatusList: MutableList<BaseStatus>? = null

        init {
            mStatusList = mutableListOf()
        }

        fun getAllStatus():MutableList<BaseStatus>?{
            return mStatusList
        }

        fun addStatus(status: BaseStatus): Builder {
            mStatusList?.add(status)
            return this
        }

        fun build(): StatusSwitcher {
            return  StatusSwitcher().apply {
                this.build(this@Builder)
            }
        }
    }
}