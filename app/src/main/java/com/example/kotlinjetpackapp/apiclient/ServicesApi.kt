package com.example.kotlinjetpackapp.apiclient

import com.example.kotlinjetpackapp.application.model.Post
import com.google.gson.JsonObject
import retrofit2.http.*

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
interface ServicesApi {

    @GET
    suspend fun getAllPosts(@Url url: String): List<Post>

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id:String, @Body requestBody: JsonObject): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id:String):Post

    @POST("post")
    suspend fun addPost(@Body post: Post):Post

}