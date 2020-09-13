package com.test.empdirect

import android.app.Application

import com.test.empdirect.db.AppDB
import com.test.empdirect.db.AppDao

class EmployeeApp: Application() {

    lateinit var appDAO: AppDao
    companion object {
        lateinit var instance: EmployeeApp
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        appDAO = AppDB.getInstance(applicationContext)!!.appDao()
    }
}