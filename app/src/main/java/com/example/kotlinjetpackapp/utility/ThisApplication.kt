package com.example.kotlinjetpackapp.utility

import android.app.Application
import android.widget.Toast

/**
 * created by Ramanuj Kesharawani on 22/11/19
 */
class ThisApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance=this
    }
    companion object {
        var mInstance:ThisApplication?=null

        fun getAppInstance(): ThisApplication {
            return mInstance!!
        }

        fun showToast(msg:String){
            Toast.makeText(mInstance,""+msg,Toast.LENGTH_LONG).show()
        }
    }

}