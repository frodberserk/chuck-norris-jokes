package com.frodberserk.chucknorrisjokes.presenter

import com.frodberserk.chucknorrisjokes.BaseApplication
import com.frodberserk.chucknorrisjokes.SplashContract
import com.frodberserk.chucknorrisjokes.view.activities.MainActivity
import ru.terrakok.cicerone.Router

class SplashPresenter(var view: SplashContract.View?) : SplashContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onViewCreated() {
        router?.navigateTo(MainActivity.TAG)
        view?.finishView()
    }

    override fun onDestroy() {
        view = null
    }

}