package org.pcc.arch_mvvm

import android.app.Application

class MyGitApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyGitApp
    }
}