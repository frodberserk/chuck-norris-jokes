package com.frodberserk.chucknorrisjokes

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class BaseApplication : Application() {
    companion object {
        lateinit var INSTANCE: BaseApplication
        val CICERONE_TAG = "cicerone"
    }

    init {
        INSTANCE = this
    }

    lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initCicerone()
    }

    private fun initCicerone() {
        this.cicerone = Cicerone.create()
    }
}