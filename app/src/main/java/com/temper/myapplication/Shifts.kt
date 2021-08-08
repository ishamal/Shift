package com.temper.myapplication

import android.app.Activity
import android.app.Application

class Shifts : Application() {

    private var mCurrentActivity: Activity? = null

    init {
        instance = this
    }

    companion object {
        private var instance: Shifts? = null

        fun applicationContext(): Shifts {
            return instance as Shifts
        }
    }

    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }

}