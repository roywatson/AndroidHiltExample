package com.delasystems.androidhiltexampla

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RoysApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        private lateinit var instance: RoysApplication
        fun get(): RoysApplication {
            return instance
        }
    }

}
