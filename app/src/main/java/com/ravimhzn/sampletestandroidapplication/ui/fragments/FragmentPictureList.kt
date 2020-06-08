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
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.ui.UICommunicationListener
import com.ravimhzn.sampletestandroidapplication.ui.fragments.adapter.PhotoListAdapter
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.MainViewModel
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.setAlbumListResponse
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.setLayoutManagerState
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_frag_user_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FragmentPictureList(
    private val viewModelFactory: ViewModelProvider.Factory
) :
    Fragment(R.layout.fragment_picture_list),
    PhotoListAdapter.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    private val TAG = "AppDebug ->"

    private val CLASS_NAME = "FragmentUserList"

    lateinit var uiCommunicationListener: UICommunicationListener

    private lateinit var recyclerAdapter: PhotoListAdapter

    val viewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }

    val observer: Observer<MainViewState> = Observer { viewState ->
        if (viewState != null) {
            viewState.fragmentUserList.userListResponse?.let { userListResponse ->
                initData(userListResponse.id)
            }

            viewState.fragmentPictureList.let { fragmentPictureList ->
                fragmentPictureList.arrAlbumListResponse?.let { list ->
                    recyclerAdapter.apply {
                        submitList(list)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObservers()
    }

    private fun initData(id: Int?) {
        val viewState = viewModel.getCurrentViewStateOrNew()
        if (viewState.fragmentPictureList.arrAlbumListResponse == null) {
            id?.let { MainStateEvent.GetPictureList(it) }
                ?.let { viewModel.setStateEvent(stateEvent = it) }
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, observer)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FragmentPictureList.context)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            removeItemDecoration(topSpacingDecorator) // does nothing if not applied already
            addItemDecoration(topSpacingDecorator)

            recyclerAdapter = PhotoListAdapter(
                interaction = this@FragmentPictureList
            )
            adapter = recyclerAdapter
        }
    }

    override fun onRefresh() {
        subscribeObservers()
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

    override fun onItemSelected(position: Int, item: AlbumListResponse) {
        removeViewStateObserver()
        viewModel.setAlbumListResponse(item)
        var bundle = Bundle()
        bundle.putString("title", "")
        findNavController().navigate(
            R.id.action_fragmentPictureList_to_fragmentPictureDetails,
            bundle
        )
    }

    private fun removeViewStateObserver() {
        viewModel.viewState.removeObserver(observer)
    }

    override fun restoreListPosition() {
        restoreLayoutManager()
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
