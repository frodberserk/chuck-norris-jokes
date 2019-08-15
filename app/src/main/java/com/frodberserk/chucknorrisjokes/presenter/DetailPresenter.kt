package com.frodberserk.chucknorrisjokes.presenter

import com.frodberserk.chucknorrisjokes.BaseApplication
import com.frodberserk.chucknorrisjokes.DetailContract
import com.frodberserk.chucknorrisjokes.entity.Joke
import com.frodberserk.chucknorrisjokes.view.activities.MainActivity
import ru.terrakok.cicerone.Router

class DetailPresenter(var view: DetailContract.View?) : DetailContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun backButtonClicked() {
        router?.backTo(MainActivity.TAG)
    }

    override fun onViewCreated(joke: Joke) {
        view?.showJokeData(joke.id.toString(), joke.text ?: "")
    }

    override fun onDestroy() {
        view = null
    }
}