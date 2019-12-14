package com.example.kotlinjetpackapp.application.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinjetpackapp.R
import com.example.kotlinjetpackapp.apiclient.ApiClient
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.application.model.repository.PostListRepo
import com.example.kotlinjetpackapp.application.viewmodel.PostListViewModel
import com.example.kotlinjetpackapp.databinding.ActivityPostDetailBinding
import com.example.kotlinjetpackapp.roomdb.AppDatabase

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityPostDetailBinding=DataBindingUtil.setContentView(this,R.layout.activity_post_detail)
        val post=intent.getSerializableExtra("post") as Posts
        val update=intent.getBooleanExtra("updated",false)
        if(!update){
            binding.btnUpdate.text="Add"
        }

        binding.post=post

        binding.btnUpdate.setOnClickListener {
            val intent= Intent()
            intent.putExtra("updated_post",post)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}
