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

    override fun onBackPressed() {
        var fragment: BaseFragment? = null
        for (fr in supportFragmentManager.fragments.reversed()) {
            if (fr is BaseFragment) {
                fragment = fr
                break
            }
        }

        if (fragment != null) {
            if (!fragment.onBackPressed())
                super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

}