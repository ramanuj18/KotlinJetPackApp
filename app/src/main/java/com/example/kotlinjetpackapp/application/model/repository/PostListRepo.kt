package com.example.kotlinjetpackapp.application.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinjetpackapp.apiclient.ServicesApi
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.application.model.daoclasses.PostsDao
import com.example.kotlinjetpackapp.utility.AppUtility
import retrofit2.HttpException

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
class PostListRepo(private val apiService: ServicesApi, private val postsDao: PostsDao) {

    suspend fun getAllPosts() {
            val postList = apiService.getAllPosts("/posts")
            postsDao.insertAllPosts(postList)
    }

    fun getAllPostsFromLocalDb(): LiveData<List<Posts>> {
        return postsDao.getAllPosts()
    }

    suspend fun addPost(post: Posts){
            val posts=apiService.addPost(post)
            postsDao.insertPost(posts)
    }

    suspend fun updatePost(post: Posts){
            val posts=apiService.updatePost(post.id.toString(),AppUtility.getJsonObject(post))
            postsDao.updatePost(posts)
    }

    suspend fun deletePost(id: String){
            val posts=apiService.deletePost(id)
            postsDao.deletePost(id)
    }
}