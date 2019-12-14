package com.example.kotlinjetpackapp.utility

/**
 * created by Ramanuj Kesharawani on 22/11/19
 */
class AppConstant {
    companion object{
        //Room DB
        const val ROOM_DB_VERSION=1
        const val ROOM_DB_NAME="posts_db"
        const val POST_TABLE_NAME="posts"

        //Api request url
        const val getAllPosts="/posts"
        const val getEmployeeDetail="/employee/1"

    }
}