package com.frodberserk.chucknorrisjokes.view.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.frodberserk.chucknorrisjokes.BaseApplication
import com.frodberserk.chucknorrisjokes.DetailContract
import com.frodberserk.chucknorrisjokes.R
import com.frodberserk.chucknorrisjokes.entity.Joke
import com.frodberserk.chucknorrisjokes.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command

class DetailActivity : BaseActivity(), DetailContract.View {
    companion object {
        val TAG = "DetailActivity"
        val EXTRA_JOKE = "extra_joke"
    }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is BackTo) {
                    back()
                }
            }

            private fun back() {
                finish()
            }
        }
    }

    private var presenter: DetailPresenter? = null

    override fun showJokeData(id: String, joke: String) {
        tvJokeId.text = id
        tvJoke.text = joke
    }

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val tvJokeId: TextView by lazy { tv_joke_id_activity_detail }
    private val tvJoke: TextView by lazy { tv_joke_activity_detail }

    override fun getToolbarInstance() = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = DetailPresenter(this)

        setSupportActionBar(toolbar)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onResume() {
        super.onResume()
        val arg = intent.getParcelableExtra<Joke>(EXTRA_JOKE)
        arg?.let {
            presenter?.onViewCreated(it)
        }
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                presenter?.backButtonClicked()
                true
            }
            else -> false
        }
    }
}