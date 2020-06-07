package com.ravimhzn.sampletestandroidapplication.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.appbar.AppBarLayout
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.di.BaseApplication
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.ErrorDialogCallback
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.UICommunicationListener
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.displayErrorDialog
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.MainViewModelTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MAIN_VIEW_STATE_BUNDLE_KEY
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainViewStateTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.ERROR_STACK_BUNDLE_KEY
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.ErrorStack
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.ErrorState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), UICommunicationListener {

    private val TAG = "AppDebug ->"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: MainViewModelTest by viewModels {
        viewModelFactory
    }

    // keep reference of dialogs for dismissing if activity destroyed
    // also prevent recreation of same dialog when activity recreated
    private val dialogs: HashMap<String, MaterialDialog> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        subscribeObservers()

//        restoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.clearActiveJobCounter() //Slickest way to handle data during orientation ^_^
        outState.putParcelable(
            MAIN_VIEW_STATE_BUNDLE_KEY,
            viewModel.getCurrentViewStateOrNew()
        )
        outState.putParcelableArrayList(
            ERROR_STACK_BUNDLE_KEY,
            viewModel.errorStack
        )
        super.onSaveInstanceState(outState)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let { inState ->
            (inState[MAIN_VIEW_STATE_BUNDLE_KEY] as MainViewStateTest?)?.let { viewState ->
                viewModel.setViewState(viewState)
            }
            (inState[ERROR_STACK_BUNDLE_KEY] as ArrayList<ErrorState>?)?.let { stack ->
                val errorStack = ErrorStack()
                errorStack.addAll(stack)
                viewModel.setErrorStack(errorStack)
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(this, Observer { viewState ->
            if (viewState != null) {
                displayMainProgressBar(viewModel.areAnyJobsActive()) //Slickest way to handle progressbar even if there are 1000s active jobs parallelly
            }
        })

        viewModel.errorState.observe(this, Observer { errorState ->
            errorState?.let {
                displayErrorMessage(errorState)
            }
        })
    }

    private fun setupActionBar() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        tool_bar.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed() //Handles actionbar back button feature
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayErrorMessage(errorState: ErrorState) {
        if (!dialogs.containsKey(errorState.message)) {
            dialogs.put(
                key = errorState.message,
                value = displayErrorDialog(errorState.message,
                    object : ErrorDialogCallback {
                        override fun clearError() {
                            viewModel.clearError(0)
                        }
                    })
            )
        }
    }

    override fun displayMainProgressBar(isLoading: Boolean) {
        if (isLoading) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    override fun hideToolbar() {
        tool_bar.visibility = View.GONE
    }

    override fun showToolbar() {
        tool_bar.visibility = View.VISIBLE
    }

    override fun hideStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        hideToolbar()
    }

    override fun showStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        showToolbar()
    }

    override fun expandAppBar() {
        findViewById<AppBarLayout>(R.id.app_bar).setExpanded(true)
    }

    override fun onDestroy() {
        cleanUpOnDestroy()
        super.onDestroy()
    }

    private fun cleanUpOnDestroy() {
        for (dialog in dialogs) {
            dialog.value.dismiss()
        }
    }
}
