package com.magre.challenge.ui.common

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var currentFragment : Fragment? = null
}
