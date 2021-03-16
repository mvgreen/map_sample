package com.mvgreen.maptest.ui

import android.os.Bundle
import com.mvgreen.maptest.R
import com.mvgreen.maptest.ui.base.AppActivity

class MainActivity : AppActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0.0f
    }

}