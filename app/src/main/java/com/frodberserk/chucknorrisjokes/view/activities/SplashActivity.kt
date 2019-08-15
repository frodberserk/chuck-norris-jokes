package com.frodberserk.chucknorrisjokes.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Start 'MainActivity'
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        // close splash activity
        finish()
    }
}