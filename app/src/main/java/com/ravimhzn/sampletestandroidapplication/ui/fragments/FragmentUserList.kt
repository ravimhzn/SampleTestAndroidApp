package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import com.ravimhzn.sampletestandroidapplication.ui.fragments.adapter.UserListAdapter
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.state.setUserList
import com.ravimhzn.sampletestandroidapplication.ui.state.setUserListResponse
import com.ravimhzn.sampletestandroidapplication.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_frag_user_list.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentUserList : MainBaseFragment(), UserListAdapter.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recyclerAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStateEvent(MainStateEvent.GetUserListEvent()) //Fire the stateEvent
        swipeRefresh.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObservers()
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

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            if (dataState != null) {
                stateChangeListener.onDataStateChange(dataState)
                dataState.data?.let {
                    it.data?.let {
                        it.getContentIfNotHandled()?.let {
                            Log.d(TAG, "FragmentUserList, DataState: ${it}")
                            viewModel.setUserList(it.userList)
                        }
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            Log.d(TAG, "FragmentUserList, ViewState: ${viewState.userList}")
            if (viewState != null) {
                recyclerAdapter.submitList(
                    list = viewState.userList.arrUserList
                )
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter =
            null   // clear references otherwise it can leak memory -> I have No idea how
    }

    override fun onItemSelected(position: Int, item: UserListResponse) {
        Log.d(TAG, "onItemSelected: $item")
        viewModel.setUserListResponse(item)
        var bundle = Bundle()
        item.id?.let {
            bundle.putString(
                "title",
                "Albumn ID: $it"
            ) //should match the name of label on nav_graph
        }
        findNavController().navigate(R.id.action_fragmentUserList_to_fragmentPictureList, bundle)
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
