package com.ravimhzn.sampletestandroidapplication.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.di.ViewModelProviderFactory
import com.ravimhzn.sampletestandroidapplication.ui.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), DataStateChangeListener,
    NavController.OnDestinationChangedListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

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
        viewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        findNavController(R.id.main_nav_host_fragment).addOnDestinationChangedListener(this) //Cancel any active jobs
    }

    private fun setupActionBar() {
        setSupportActionBar(tool_bar)
        val navController = findNavController(R.id.main_nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        /**
         * We want to cancel the jobs when users press back before api request completes
         * We need to do this otherwise when request completes, code inside that request will get called on background thread
         */
        viewModel.cancelActiveJobs()
        app_bar.setExpanded(true) //NOTE: (for the appbar disappearance bug fix)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed() //Handles actionbar back button feature
        }
        return super.onOptionsItemSelected(item)
    }
}
