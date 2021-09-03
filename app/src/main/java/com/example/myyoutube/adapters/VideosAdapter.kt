package com.example.myyoutube.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myyoutube.R
import com.example.myyoutube.VideosFragment
import com.example.myyoutube.databinding.VideosChannelsRowLayoutBinding
import com.example.myyoutube.youtubeAPI.videosResponse.Item
import com.squareup.picasso.Picasso

class VideosAdapter(val onArticleClicked: (slug: String) -> Unit) : ListAdapter<Item, VideosAdapter.ViewHolder >(
    object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }
) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.videos_channels_row_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        VideosChannelsRowLayoutBinding.bind(holder.itemView).apply {
            val singleresponse = getItem(position)

            titleTextView.text = singleresponse.snippet.title
            descriptionTextView.text = singleresponse.snippet.description
            getphotoResult( singleresponse.snippet.thumbnails.default.url, picimageView)

            root.setOnClickListener { onArticleClicked(singleresponse.id.videoId) }
        }
    }

}

fun getphotoResult(reference:String?, Imageview : ImageView)  {
    Picasso.get()
        .load(reference)
        .into(Imageview)
}