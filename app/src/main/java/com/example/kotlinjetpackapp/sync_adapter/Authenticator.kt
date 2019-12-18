package com.example.kotlinjetpackapp.sync_adapter

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.NetworkErrorException
import android.content.Context
import android.os.Bundle

/**
 * created by Ramanuj Kesharawani on 16/12/19
 */
class Authenticator(context: Context) : AbstractAccountAuthenticator(context) {

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(p0: AccountAuthenticatorResponse?, p1: Account?, p2: Bundle?): Bundle? = null

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(p0: AccountAuthenticatorResponse?, p1: Account?, p2: String?, p3: Bundle?): Bundle {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(p0: AccountAuthenticatorResponse?, p1: Account?, p2: String?, p3: Bundle?): Bundle {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(p0: AccountAuthenticatorResponse?, p1: Account?, p2: Array<out String>?): Bundle {
        throw UnsupportedOperationException()
    }

    override fun editProperties(p0: AccountAuthenticatorResponse?, p1: String?): Bundle {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun addAccount(
        p0: AccountAuthenticatorResponse?,
        p1: String?,
        p2: String?,
        p3: Array<out String>?,
        p4: Bundle?
    ): Bundle? = null

    override fun getAuthTokenLabel(p0: String?): String {
        throw UnsupportedOperationException()
    }
}