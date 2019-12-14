package com.example.kotlinjetpackapp.apiclient

import com.example.kotlinjetpackapp.application.model.Posts
import com.google.gson.JsonObject
import retrofit2.http.*

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
interface ServicesApi {

    @GET
    suspend fun getAllPosts(@Url url: String): List<Posts>

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id:String, @Body requestBody: JsonObject): Posts

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id:String):Posts

    @POST("posts")
    suspend fun addPost(@Body posts: Posts):Posts

}