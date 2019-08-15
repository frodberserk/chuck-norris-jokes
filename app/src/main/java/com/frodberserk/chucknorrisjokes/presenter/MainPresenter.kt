package com.frodberserk.chucknorrisjokes.presenter

import com.frodberserk.chucknorrisjokes.BaseApplication
import com.frodberserk.chucknorrisjokes.MainContract
import com.frodberserk.chucknorrisjokes.entity.Joke
import com.frodberserk.chucknorrisjokes.interactor.MainInteractor
import com.frodberserk.chucknorrisjokes.view.activities.DetailActivity
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.terrakok.cicerone.Router

class MainPresenter(var view: MainContract.View?) : MainContract.Presenter, MainContract.InteractorOutput {

    private var interactor: MainContract.Interactor? = MainInteractor()
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onQuerySuccess(data: List<Joke>) {
        view?.hideLoading()
        view?.publishDataList(data)
    }

    override fun onQueryError() {
        view?.hideLoading()
        view?.showInfoMessage("Some error occurred")
    }

    override fun listItemClicked(joke: Joke?) {
//        view?.showInfoMessage(joke?.component2() ?: "")
        router?.navigateTo(DetailActivity.TAG, joke)
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadJokesList { result ->
            when (result) {
                is Result.Failure -> onQueryError()
                is Result.Success -> {
                    val jokesJsonObject = result.get().obj()
                    val type = object : TypeToken<List<Joke>>() {}.type
                    val jokesList: List<Joke> =
                        Gson().fromJson(jokesJsonObject.getJSONArray("value").toString(), type)
                    onQuerySuccess(jokesList)
                }
            }
        }
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}