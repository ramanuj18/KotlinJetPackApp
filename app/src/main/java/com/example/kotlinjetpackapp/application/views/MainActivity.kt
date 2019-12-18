package com.example.kotlinjetpackapp.application.views

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinjetpackapp.R
import com.example.kotlinjetpackapp.sync_adapter.SyncAdapterManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
       lateinit var syncAdapterManager:SyncAdapterManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_force_sync.setOnClickListener {

        }
    }
}
