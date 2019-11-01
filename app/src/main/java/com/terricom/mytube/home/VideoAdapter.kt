package com.terricom.mytube.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.terricom.mytube.BitmapWorkerTask
import com.terricom.mytube.MyTubeApp
import com.terricom.mytube.R
import com.terricom.mytube.data.Video
import com.terricom.mytube.databinding.ItemVideoBinding

class VideoAdapter(

    val viewModel: HomeViewModel

) : ListAdapter<Video, VideoAdapter.VideoViewHolder>(DiffCallback) {

    class VideoViewHolder(
        var binding: ItemVideoBinding
    ): RecyclerView.ViewHolder(binding.root), LifecycleOwner {

        fun bind(video: Video) {

            binding.lifecycleOwner =this
            binding.video = video

            binding.videoImage.startAnimation(AnimationUtils.loadAnimation(MyTubeApp.applicationContext(), R.anim.animation))
            binding.executePendingBindings()
        }

        private val lifecycleRegistry = LifecycleRegistry(this)

        init {
            lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        }

        fun markAttach() {
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
        }

        fun markDetach() {
            lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        }

        override fun getLifecycle(): Lifecycle {
            return lifecycleRegistry
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.title == newItem.title && oldItem.img == newItem.img
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {

        return VideoViewHolder(ItemVideoBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val product = getItem(position)

        val task = BitmapWorkerTask(holder.binding.videoImage, product.img)
        task.execute()

        holder.bind(product)
    }
    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }
}