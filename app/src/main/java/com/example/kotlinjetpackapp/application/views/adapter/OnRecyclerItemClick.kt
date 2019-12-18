package com.example.kotlinjetpackapp.application.views.adapter

import com.example.kotlinjetpackapp.application.model.Post

/**
 * created by Ramanuj Kesharawani on 11/12/19
 */
interface OnRecyclerItemClick {
    fun onItemClick(post:Post)
    fun onItemLongClick(post: Post)
}