package com.ravimhzn.sampletestandroidapplication.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ravimhzn.sampletestandroidapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), DataStateChangeListener {

    override fun displayProgressBar(bool: Boolean) {
        if (bool) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(tool_bar)
    }
}
