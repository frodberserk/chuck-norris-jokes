package com.frodberserk.chucknorrisjokes

import com.frodberserk.chucknorrisjokes.entity.Joke
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result

interface MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishDataList(data: List<Joke>)
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun listItemClicked(joke: Joke?)

        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }

    interface Interactor {
        fun loadJokesList(output: (result: Result<Json, FuelError>) -> Unit)
    }

    interface InteractorOutput {
        fun onQuerySuccess(data: List<Joke>)
        fun onQueryError()
    }
}