package com.ravimhzn.sampletestandroidapplication.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.sampletestandroidapplication.R
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import kotlinx.android.synthetic.main.layout_user_list_item.view.*

class UserListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserListResponse>() {

        override fun areItemsTheSame(
            oldItem: UserListResponse,
            newItem: UserListResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserListResponse,
            newItem: UserListResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UserListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_user_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserListViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<UserListResponse>) {
        differ.submitList(list)
    }

    class UserListViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UserListResponse) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.tvId.text = "ID: ${item.id.toString()}"
            itemView.tvName.text = "Name: ${item.name}"
            itemView.tvEmail.text = "Email: ${item.email}"
            itemView.tvPhone.text = "Phone: ${item.phone}"
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: UserListResponse)
    }
}