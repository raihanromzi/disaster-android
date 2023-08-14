package com.gigih.disasterapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gigih.disasterapp.data.remote.response.GeometriesItem
import com.gigih.disasterapp.databinding.DisasterItemBinding

class DisasterAdapter :
    ListAdapter<GeometriesItem, DisasterAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            DisasterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val disaster = getItem(position)
        holder.bind(disaster)
    }

    class MyViewHolder(private val binding: DisasterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(disaster: GeometriesItem) {

            // check if disaster is null
            if (disaster.properties.title.toString().length < 2) {
                binding.tvDisasterItem.text = "Terjadi Bencana Alam"
            } else {
                binding.tvDisasterItem.text = disaster.properties.title
            }

            binding.tvCoordinateDisasterItem.text = disaster.coordinates.toString()


            // using glide to load image from url to imageview
            Glide.with(itemView.context)
                .load(disaster.properties.imageUrl)
                // handle error
                .apply(RequestOptions().error(com.google.maps.android.R.drawable.common_google_signin_btn_icon_light_normal_background))
                .into(binding.ivDisasterItem)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GeometriesItem>() {
            override fun areItemsTheSame(
                oldItem: GeometriesItem, newItem: GeometriesItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GeometriesItem, newItem: GeometriesItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}