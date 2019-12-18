package com.example.kotlinjetpackapp.application.model.repository

import androidx.lifecycle.LiveData
import com.example.kotlinjetpackapp.apiclient.ServicesApi
import com.example.kotlinjetpackapp.application.model.Post
import com.example.kotlinjetpackapp.application.model.daoclasses.PostsDao
import com.example.kotlinjetpackapp.utility.AppUtility

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
class PostListRepo(private val apiService: ServicesApi, private val postsDao: PostsDao) {

    suspend fun getAllPosts() {
            val postList = apiService.getAllPosts("/posts")
            postsDao.insertAllPosts(postList)
    }

    fun getAllPostsFromLocalDb(): LiveData<List<Post>> {
        return postsDao.getAllPosts()
    }

    suspend fun addPost(post: Post){
            val posts=apiService.addPost(post)
            postsDao.insertPost(posts)
    }

    suspend fun updatePost(post: Post){
            val posts=apiService.updatePost(post.id.toString(),AppUtility.getJsonObject(post))
            postsDao.updatePost(posts)
    }

    suspend fun deletePost(id: String){
            val posts=apiService.deletePost(id)
            postsDao.deletePost(id)
    }
}