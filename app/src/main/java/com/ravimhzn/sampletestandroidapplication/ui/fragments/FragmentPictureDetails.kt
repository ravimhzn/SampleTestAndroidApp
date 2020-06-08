package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.espressodaggerexamples.util.GlideManager
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.ui.UICommunicationListener
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.MainViewModel
import com.ravimhzn.sampletestandroidapplication.utils.extension.setImageUrl
import kotlinx.android.synthetic.main.fragment_picture_details.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FragmentPictureDetails
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val requestManager: GlideManager
) : Fragment(R.layout.fragment_picture_details) {

    private val CLASS_NAME = "FragmentPictureDetails"

    lateinit var uiCommunicationListener: UICommunicationListener

    val viewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiCommunicationListener = (context as UICommunicationListener)
        } catch (e: Exception) {
            Log.e(CLASS_NAME, "$context must implement UICommunicationListener")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        uiCommunicationListener.showStatusBar()
        uiCommunicationListener.expandAppBar()
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            if (viewState != null) {
                viewState.fragmentPictureList.albumListResponse?.let { albumListResponse ->
                    setPhotoProperties(albumListResponse)
                }
            }
        })
    }

    private fun setPhotoProperties(albumListResponse: AlbumListResponse) {
//        albumListResponse.url?.let {
//            requestManager
//                .setImage(it, imgPhoto)
//        } //Glide doesn't work somehow
        albumListResponse.url?.let { imgPhoto.setImageUrl(it) }
        tvImageText.text = albumListResponse.title
        tvAlbumnPhoto.text =
            "Album ID: ${albumListResponse.albumId}\nPhoto ID: ${albumListResponse.id}"
    }
}
