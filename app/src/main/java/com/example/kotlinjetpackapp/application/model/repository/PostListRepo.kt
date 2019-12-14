package com.example.kotlinjetpackapp.application.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinjetpackapp.apiclient.ServicesApi
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.application.model.daoclasses.PostsDao
import com.example.kotlinjetpackapp.utility.AppUtility

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
class PostListRepo(private val apiService: ServicesApi, private val postsDao: PostsDao) {

    suspend fun getAllPosts() {
        try {
            val postList = apiService.getAllPosts("/posts")
            postsDao.insertAllPosts(postList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllPostsFromLocalDb(): LiveData<List<Posts>> {
        return postsDao.getAllPosts()
    }

    suspend fun addPost(post: Posts){
        try{
            val posts=apiService.addPost(post)
            postsDao.insertPost(posts)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    suspend fun updatePost(post: Posts){
        try {
            val posts=apiService.updatePost(post.id.toString(),AppUtility.getJsonObject(post))
            postsDao.updatePost(posts)
        }
        catch (e:Exception){
          e.printStackTrace()
        }
    }

    suspend fun deletePost(id: String){
        try{
            val posts=apiService.deletePost(id)
            postsDao.deletePost(id)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}