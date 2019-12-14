package com.example.kotlinjetpackapp.roomdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.application.model.daoclasses.PostsDao
import com.example.kotlinjetpackapp.utility.AppConstant
import com.example.kotlinjetpackapp.utility.ThisApplication

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
@Database(entities = [Posts::class], version = AppConstant.ROOM_DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke() = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase().also { instance = it }
        }

        private fun buildDatabase() = Room.databaseBuilder(
            ThisApplication.getAppInstance(),
            AppDatabase::class.java, AppConstant.ROOM_DB_NAME
        ).allowMainThreadQueries()
            .build()
    }
}