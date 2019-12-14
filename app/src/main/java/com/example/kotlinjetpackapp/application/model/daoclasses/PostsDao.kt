package com.example.kotlinjetpackapp.application.model.daoclasses

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.utility.AppConstant

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(stop: List<Posts>): List<Long>

    @Query("SELECT * FROM " + AppConstant.POST_TABLE_NAME)
    fun getAllPosts(): LiveData<List<Posts>>

    @Query("SELECT COUNT(*) FROM " + AppConstant.POST_TABLE_NAME)
    fun count(): Long

    @Update
    fun updatePost(posts: Posts): Int

    @Query("DELETE FROM " + AppConstant.POST_TABLE_NAME + " WHERE id = :id")
    fun deletePost(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Posts):Long
}