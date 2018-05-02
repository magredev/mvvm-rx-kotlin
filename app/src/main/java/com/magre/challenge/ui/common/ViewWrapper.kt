package com.magre.challenge.ui.common

import android.support.v7.widget.RecyclerView
import android.view.View

class ViewWrapper<out V: View>(val view: V) : RecyclerView.ViewHolder(view)
