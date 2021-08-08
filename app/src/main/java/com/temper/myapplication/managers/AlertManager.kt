package com.temper.myapplication.managers

import android.app.Activity
import android.view.Gravity
import com.tapadoo.alerter.Alerter
import com.temper.myapplication.R

object AlertManager {
    private var mActivity: Activity? = null

    fun setActivity(activity: Activity) {
        mActivity = activity
    }

    private fun getActivity(): Activity? = mActivity

    fun showErrorAlert(
        activity: Activity? = getActivity(),
        message: String
    ): Alerter {
        return Alerter.create(activity!!)
            .setText(message)
            .enableVibration(true)
            .hideIcon().setContentGravity(Gravity.CENTER)
            .setBackgroundColorRes(R.color.error_primary)
            .setTextAppearance(R.style.alerterTextESW)
            .setDuration(2500)
    }

    fun showWarningAlert(
        activity: Activity? = getActivity(),
        message: String
    ): Alerter {
        return Alerter.create(activity!!)
            .setText(message)
            .enableVibration(true)
            .hideIcon().setContentGravity(Gravity.CENTER)
            .setBackgroundColorRes(R.color.warning_primary)
            .setTextAppearance(R.style.alerterTextWarning)
            .setDuration(5000)
    }

}