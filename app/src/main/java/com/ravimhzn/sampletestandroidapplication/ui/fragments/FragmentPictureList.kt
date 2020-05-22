package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent

/**
 * A simple [Fragment] subclass.
 */
class FragmentPictureList : MainBaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
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
                    Log.e(TAG, "subscribeObservers: StatEvent Called")
                }
            }

            viewState.photoAlbumnList.arrPhotoAlbum?.let {
                for (i in viewState.photoAlbumnList.arrPhotoAlbum) {
                    Log.d(TAG, "${i.title}")
                }
            }
        })
    }
}
