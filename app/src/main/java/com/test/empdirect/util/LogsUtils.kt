package com.test.empdirect.util

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.test.empdirect.BuildConfig


object LogsUtils {


    internal fun makeLogD(TAG: String, message: String?) {
        if (BuildConfig.LOG_STATUS)
            Log.d(TAG, "" + message)
    }

    internal fun makeLogE(TAG: String, message: String?) {
        if (BuildConfig.LOG_STATUS)
            Log.e(TAG, "" + message)
    }

    internal fun showToast(activity: Activity?, message: String) {
        Toast.makeText(activity!!, "" + message, Toast.LENGTH_SHORT).show()
    }


}
