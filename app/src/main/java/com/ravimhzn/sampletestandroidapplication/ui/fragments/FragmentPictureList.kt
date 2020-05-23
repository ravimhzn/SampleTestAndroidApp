package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.network.responses.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.ui.fragments.adapter.PhotoListAdapter
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.state.setAlbumListResponse
import com.ravimhzn.sampletestandroidapplication.ui.state.setPhotoAlbumList
import com.ravimhzn.sampletestandroidapplication.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_frag_user_list.*

class FragmentPictureList : MainBaseFragment(), PhotoListAdapter.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recyclerAdapter: PhotoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObservers()
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

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            stateChangeListener.onDataStateChange(dataState)
            dataState.data?.let {
                it.data?.let {
                    it.getContentIfNotHandled()?.let {
                        Log.d(TAG, "FragmentPictureList, DataState: ${it}")
                        viewModel.setPhotoAlbumList(it.photoAlbumnList)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.photoAlbumnList.userListResponse?.let { userListResponse ->
                userListResponse.id?.let { id ->
                    viewModel.setStateEvent(MainStateEvent.GetPhotoAlbumListEvent(id))
                }
            }

            viewState.photoAlbumnList.arrPhotoAlbum?.let { arrAlbumnList ->
                arrAlbumnList?.let {
                    recyclerAdapter.submitList(it)
                }
            }
        })
    }

    override fun onItemSelected(position: Int, item: AlbumListResponse) {
        viewModel.setAlbumListResponse(item)
        var bundle = Bundle()
        bundle.putString("title", "")
        findNavController().navigate(
            R.id.action_fragmentPictureList_to_fragmentPictureDetails,
            bundle
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        resetUI()
        swipeRefresh.isRefreshing = false
    }

    private fun resetUI() {
        /**
         * We can make network call on it but gonna leave it as it is.
         */
        recyclerView.smoothScrollToPosition(0)
    }
}
