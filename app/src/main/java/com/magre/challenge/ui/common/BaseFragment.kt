package com.magre.challenge.ui.common

import android.support.v4.app.Fragment
import com.magre.challenge.ui.MainActivity

open class BaseFragment : Fragment() {

    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onResume() {
        super.onResume()
        mainActivity.currentFragment = this
    }

    override fun onStop() {
        super.onStop()
        mainActivity.currentFragment = null
    }
}
