package com.example.kotlinjetpackapp.utility

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
class AppUtility {
    companion object{

        fun getJsonObject(requestBody:Any):JsonObject{
           return JsonParser().parse(Gson().toJson(requestBody)).asJsonObject
        }

        fun isInternetConnected(): Boolean {
            val cm = ThisApplication.getAppInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }


    }
}

