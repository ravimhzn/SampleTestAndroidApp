package com.ravimhzn.sampletestandroidapplication.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.utils.extension.setImageUrl
import kotlinx.android.synthetic.main.layout_image_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhotoListAdapter(
    private val interaction: Interaction? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlbumListResponse>() {

        override fun areItemsTheSame(
            oldItem: AlbumListResponse,
            newItem: AlbumListResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AlbumListResponse,
            newItem: AlbumListResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PhotoListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_image_list,
                parent,
                false
            ),
            interaction = interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotoListViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<AlbumListResponse>) {
        val commitCallback = Runnable {
            /*
                if process died or nav back need to restore layoutmanager AFTER
                data is set... very annoying.
                Not sure why I need the delay... Can't figure this out. I've tested with lists
                100x the size of this one and the 100ms delay works fine.
             */
            CoroutineScope(Dispatchers.Main).launch {
                delay(100)
                interaction?.restoreListPosition()
            }
        }
        differ.submitList(list)
    }

    class PhotoListViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: AlbumListResponse) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            item.thumbnailUrl?.let { itemView.imgPhoto.setImageUrl(it) }
            itemView.tvImageText.text = "ID: ${item.title}"
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: AlbumListResponse)
        fun restoreListPosition()
    }
}