package com.example.eromatask.presetation.PostsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eromatask.databinding.PostItemBinding
import com.example.eromatask.domain.model.Post

class PostListAdapter(val onItemClicked: OnItemClicked) : ListAdapter<Post , PostListAdapter.ViewHolder>(PostDiffUtils()) {


    class ViewHolder(val binding:PostItemBinding ,val  onItemClicked: OnItemClicked) : RecyclerView.ViewHolder(binding.root){

        fun bindView(post: Post){
            binding.title.text = "${post.id}. ${post.title}"
            binding.body.text = post.body
            binding.root.setOnClickListener {
                onItemClicked.onClick(post.id)
            }
        }
    }

    class PostDiffUtils: DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false), onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}