package com.mommylicious.mobile

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MommylisiousApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MommylisiousApplication
            private set
    }
}