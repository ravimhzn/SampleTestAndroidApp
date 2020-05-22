package com.ravimhzn.sampletestandroidapplication.utils.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso


/**
 * Load image from URL via picasso. Somehow the thumbnailurl doesn't work with glide, it throws an error.
 */
fun ImageView.setImageUrl(url: String) {
    Picasso.get().load(url).into(this)
}
