package com.example.kotlinjetpackapp.sync_adapter

import android.accounts.Account
import android.content.*
import android.os.Bundle
import android.util.Log
import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TaskStackBuilder
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.content.Intent
import com.example.kotlinjetpackapp.R
import com.example.kotlinjetpackapp.apiclient.ApiClient
import com.example.kotlinjetpackapp.application.model.repository.PostListRepo
import com.example.kotlinjetpackapp.application.views.MainActivity
import com.example.kotlinjetpackapp.roomdb.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.HttpException


/**
 * created by Ramanuj Kesharawani on 16/12/19
 */
class SyncAdapter  constructor(
    context: Context,
    autoInitialize: Boolean
) : AbstractThreadedSyncAdapter(context, autoInitialize) {
    override fun onPerformSync(account: Account?, extras: Bundle?, authority: String?, provider: ContentProviderClient?, syncResult: SyncResult?) {
        Log.d("dataSyncPerform","completed $context")
        println("dataSyncPerform completed")
        showNotification(context,"","syncing data", Intent(context,MainActivity::class.java))
        val apiService=ApiClient.getServices()
        val postDao=AppDatabase.invoke().postDao()
        val postListRepo=PostListRepo(apiService,postDao)
        runBlocking {
            withContext(Dispatchers.IO){
                try {
                    postListRepo.getAllPosts()
                }catch (http:HttpException){
                    Log.d("exception","http exception")
                }catch (e:Exception){
                    Log.d("exception","other exception")
                }finally {
                    showNotification(context,"","sync complete", Intent(context,MainActivity::class.java))
                }

            }
        }
    }

    private fun showNotification(context: Context, title: String, body: String, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "channel-01"
        val channelName = "Channel Name"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
        }
        val mBuilder = NotificationCompat.Builder(
            context,
            channelId
        )
            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle(title)
            .setAutoCancel(true)
            .setContentText(body)
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntent(intent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)
        notificationManager.notify(notificationId, mBuilder.build())
    }
}