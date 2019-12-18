package com.example.kotlinjetpackapp.sync_adapter

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * created by Ramanuj Kesharawani on 16/12/19
 */
class AuthenticatorService : Service() {

    private lateinit var mAuthenticator: Authenticator
    override fun onCreate() {
        mAuthenticator = Authenticator(this)
    }

    override fun onBind(p0: Intent?): IBinder? = mAuthenticator.iBinder

}