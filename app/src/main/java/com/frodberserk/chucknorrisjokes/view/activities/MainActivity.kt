package com.frodberserk.chucknorrisjokes.view.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import com.frodberserk.chucknorrisjokes.MainContract
import com.frodberserk.chucknorrisjokes.R
import com.frodberserk.chucknorrisjokes.entity.Joke
import com.frodberserk.chucknorrisjokes.presenter.MainPresenter
import com.frodberserk.chucknorrisjokes.view.adapters.JokesListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), MainContract.View {

    private var presenter: MainPresenter? = null
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val recyclerView: RecyclerView by lazy { rv_jokes_list_activity_main }
    private val progressBar: ProgressBar by lazy { prog_bar_loading_jokes_activity_main }

    override fun showLoading() {
        recyclerView.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        recyclerView.isEnabled = true
        progressBar.visibility = View.GONE
    }

    override fun publishDataList(data: List<Joke>) {
        (recyclerView.adapter as JokesListAdapter).updateData(data)
    }

    override fun showInfoMessage(msg: String) {
        toast(msg)
    }

    override fun getToolbarInstance() = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = JokesListAdapter({
            presenter?.listItemClicked(it)
        }, null)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}