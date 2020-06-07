package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codingwithmitch.espressodaggerexamples.ui.viewmodel.getCurrentViewStateOrNew
import com.codingwithmitch.espressodaggerexamples.ui.viewmodel.getLayoutManagerState
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
import com.ravimhzn.sampletestandroidapplication.ui.UICommunicationListener
import com.ravimhzn.sampletestandroidapplication.ui.fragments.adapter.UserListAdapter
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.MainViewModel
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.setLayoutManagerState
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent.GetUserListEvent
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_frag_user_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FragmentUserList(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_frag_user_list),
    UserListAdapter.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    private val TAG = "AppDebug ->"

    private val CLASS_NAME = "FragmentUserList"

    lateinit var uiCommunicationListener: UICommunicationListener

    private lateinit var recyclerAdapter: UserListAdapter

    val viewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }

    val observer: Observer<MainViewState> = Observer { viewState ->
        if (viewState != null) {

            viewState.fragmentUserList.let { view ->
                view.arrUserList?.let { userList ->
                    recyclerAdapter.apply {
                        submitList(userList)
                    }
                    displayTheresNothingHereTV((userList.size > 0))
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObservers()
        initData()
    }

    private fun initData() {
        val viewState = viewModel.getCurrentViewStateOrNew()
        if (viewState.fragmentUserList.arrUserList == null) {
            viewModel.setStateEvent(stateEvent = GetUserListEvent())
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, observer)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FragmentUserList.context)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            removeItemDecoration(topSpacingDecorator) // does nothing if not applied already
            addItemDecoration(topSpacingDecorator)

            recyclerAdapter = UserListAdapter(
                interaction = this@FragmentUserList
            )
            adapter = recyclerAdapter
        }
    }

    private fun displayTheresNothingHereTV(isDataAvailable: Boolean) {
        if (isDataAvailable) {
            no_data_textview.visibility = View.GONE
        } else {
            no_data_textview.visibility = View.VISIBLE
        }
    }

    override fun onRefresh() {
        initData()
        swipeRefresh.isRefreshing = false
    }

    override fun onPause() {
        super.onPause()
        saveLayoutManagerState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter =
            null   // clear references otherwise it can leak memory -> I have No idea how
    }

    private fun saveLayoutManagerState() {
        recyclerView.layoutManager?.onSaveInstanceState()?.let { lmState ->
            viewModel.setLayoutManagerState(lmState)
        }
    }

    fun restoreLayoutManager() {
        viewModel.getLayoutManagerState()?.let { lmState ->
            recyclerView?.layoutManager?.onRestoreInstanceState(lmState)
        }
    }

    override fun restoreListPosition() {
        restoreLayoutManager()
    }

    override fun onItemSelected(position: Int, item: UserListResponse) {
//        Log.d(TAG, "onItemSelected: $item")
//        viewModel.setUserListResponse(item)
        var bundle = Bundle()
        item.id?.let {
            bundle.putString(
                "title",
                "Albumn ID: $it"
            ) //should match the name of label on nav_graph
        }
        findNavController().navigate(R.id.action_fragmentUserList_to_fragmentPictureList, bundle)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiCommunicationListener = (context as UICommunicationListener)
        } catch (e: Exception) {
            Log.e(CLASS_NAME, "$context must implement UICommunicationListener")
        }
    }


}
