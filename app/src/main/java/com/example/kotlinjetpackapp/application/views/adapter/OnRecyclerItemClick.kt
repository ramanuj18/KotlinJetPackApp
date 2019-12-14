package com.example.kotlinjetpackapp.application.views.adapter

import com.example.kotlinjetpackapp.application.model.Posts

/**
 * created by Ramanuj Kesharawani on 11/12/19
 */
interface OnRecyclerItemClick {
    fun onItemClick(post:Posts)
    fun onItemLongClick(post: Posts)
}