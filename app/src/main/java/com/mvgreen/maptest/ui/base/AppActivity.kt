package com.mvgreen.maptest.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AppActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AppActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

    override fun onPause() {
        super.onPause()
    }

}