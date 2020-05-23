package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.network.responses.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.utils.extension.setImageUrl
import kotlinx.android.synthetic.main.fragment_picture_details.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentPictureDetails : MainBaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            stateChangeListener.onDataStateChange(dataState)
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.viewPhotoDetails.albumListResponse?.let { albumListResponse ->
                setPhotoProperties(albumListResponse)
            }
        })
    }

    private fun setPhotoProperties(albumListResponse: AlbumListResponse) {
        albumListResponse.url?.let { imgPhoto.setImageUrl(it) }
        tvImageText.text = albumListResponse.title
        tvAlbumnPhoto.text =
            "Album ID: ${albumListResponse.albumId}\nPhoto ID: ${albumListResponse.id}"
    }
}
