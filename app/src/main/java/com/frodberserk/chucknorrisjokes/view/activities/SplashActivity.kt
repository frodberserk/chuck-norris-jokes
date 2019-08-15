package com.frodberserk.chucknorrisjokes.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.frodberserk.chucknorrisjokes.BaseApplication
import com.frodberserk.chucknorrisjokes.SplashContract
import com.frodberserk.chucknorrisjokes.presenter.SplashPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class SplashActivity : AppCompatActivity(), SplashContract.View {
    companion object {
        val TAG: String = "SplashActivity"
    }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    DetailActivity.TAG -> startActivity(
                        Intent(this@SplashActivity, MainActivity::class.java)
                    )
                    else -> Log.e(BaseApplication.CICERONE_TAG, "Unknown screen: " + command.screenKey)
                }
            }
        }
    }

    override fun finishView() {
        finish()
    }

    var presenter: SplashPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SplashPresenter(this)
        // Start 'MainActivity'
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        // close splash activity
        finish()
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }
}