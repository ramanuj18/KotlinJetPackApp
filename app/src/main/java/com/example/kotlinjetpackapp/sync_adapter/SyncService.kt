package com.example.kotlinjetpackapp.sync_adapter

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * created by Ramanuj Kesharawani on 16/12/19
 */
class SyncService : Service() {

    override fun onCreate() {
        synchronized(sSyncAdapterLock) {
            sSyncAdapter = sSyncAdapter ?: SyncAdapter(applicationContext, true)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return sSyncAdapter?.syncAdapterBinder ?: throw IllegalStateException()
    }

    companion object {
        // Storage for an instance of the sync adapter
        private var sSyncAdapter: SyncAdapter? = null
        // Object to use as a thread-safe lock
        private val sSyncAdapterLock = Any()
    }
}