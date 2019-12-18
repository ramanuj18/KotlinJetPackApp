package com.example.kotlinjetpackapp.sync_adapter

import android.accounts.Account
import android.accounts.AccountManager
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle

/**
 * created by Ramanuj Kesharawani on 16/12/19
 */
class SyncAdapterManager(context: Context) {
    companion object{
        const val AUTHORITY="com.example.kotlinjetpackapp.provider"
        const val ACCOUNT_TYPE="com.example.kotlinjetpackapp"
        const val ACCOUNT="dummyaccount"
    }

    private var newAccount: Account? = null

    init {
        newAccount= Account(ACCOUNT, ACCOUNT_TYPE)
        val accountManager=context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

        if(accountManager.addAccountExplicitly(newAccount,null,null)){
            println("Dodat acc")
        }else{
            println("Ili postoji ili je doslo do sranja")
        }
        ContentResolver.setSyncAutomatically(newAccount, AUTHORITY,true)
        val SYNC_FREQUENCY = (60 * 16).toLong()
        ContentResolver.addPeriodicSync(newAccount, AUTHORITY, Bundle(), SYNC_FREQUENCY)

    }
    fun refreshData(){
        val bundle = Bundle()
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true)
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true)

        ContentResolver.requestSync(newAccount, AUTHORITY, bundle)
    }
}