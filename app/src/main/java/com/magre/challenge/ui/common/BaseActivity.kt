package com.magre.challenge.ui.common

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {

    var currentFragment : Fragment? = null

    fun showMessage(message: String) {
        Snackbar.make(mainRootView, message, Snackbar.LENGTH_LONG).show()
    }
}
