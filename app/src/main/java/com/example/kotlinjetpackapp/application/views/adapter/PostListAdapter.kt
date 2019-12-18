package com.example.kotlinjetpackapp.application.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinjetpackapp.R
import com.example.kotlinjetpackapp.application.model.Post
import com.example.kotlinjetpackapp.databinding.PostsItemLayoutBinding

/**
 * created by Ramanuj Kesharawani on 11/12/19
 */
class PostListAdapter(var context: Context, var onRecyclerItemClick: OnRecyclerItemClick) :
    RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    var postList = emptyList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: PostsItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.posts_item_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = postList[position]
        holder.getLayoutBinding().post = posts
        holder.getLayoutBinding().root.setOnClickListener {
            onRecyclerItemClick.onItemClick(posts)
        }
        holder.getLayoutBinding().root.setOnLongClickListener {
            onRecyclerItemClick.onItemLongClick(posts)
            return@setOnLongClickListener true
        }
    }

    fun setData(list: List<Post>) {
        postList = list
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: PostsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun getLayoutBinding(): PostsItemLayoutBinding {
            return binding
        }
    }
}