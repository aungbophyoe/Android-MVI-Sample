package com.aungbophyoe.space.mvisample.recyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mvisample.R
import com.aungbophyoe.space.mvisample.model.Photo
import com.aungbophyoe.space.mvisample.util.ImageBindingAdapter
import kotlinx.android.synthetic.main.recycler_item.view.*

class PhotoRecyclerAdapter(private val context:Context) :
    ListAdapter<Photo, PhotoRecyclerAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }
){

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val imageView = view.ivPhoto
        val title = view.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = inflater.inflate(R.layout.recycler_item, parent, false)
        val viewHolder = ViewHolder(inflateView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.title.text = currentList[position].title
            ImageBindingAdapter.setImageUrl(holder.imageView,currentList[position].url)
        }catch (e:Exception){

        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}