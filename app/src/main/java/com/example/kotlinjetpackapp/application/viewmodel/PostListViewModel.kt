package com.example.kotlinjetpackapp.application.viewmodel

import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.application.model.repository.PostListRepo
import com.example.kotlinjetpackapp.application.views.adapter.PostListAdapter
import com.example.kotlinjetpackapp.utility.singleArgViewModelFactory
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
class PostListViewModel(private val postListRepo: PostListRepo) : ViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PostListViewModel)
    }

    private var mutableLiveData: LiveData<List<Posts>>? = null
    private var isLoading=MutableLiveData<Boolean>(false)

    fun getAllPosts() {
        launchDataLoad {
            postListRepo.getAllPosts()
        }
    }

    fun addPost(post: Posts){
        launchDataLoad {
            postListRepo.addPost(post)
        }
    }

    fun getAllPostsFromLocalDb(): LiveData<List<Posts>>? {
        mutableLiveData = postListRepo.getAllPostsFromLocalDb()
        return mutableLiveData
    }

    fun getIsLoading():LiveData<Boolean>{
        return isLoading
    }

    fun updatePost(post: Posts){
       launchDataLoad {
            postListRepo.updatePost(post)
        }
    }

    fun deletePost(id: String){
        launchDataLoad {
            postListRepo.deletePost(id)
        }
    }

    @BindingAdapter("data")
    fun setRecyclerViewProperties(recyclerView:RecyclerView,list:List<Posts>){
        if(recyclerView.adapter  is PostListAdapter){
            (recyclerView.adapter as PostListAdapter).setData(list)
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Unit {
        viewModelScope.launch {
            try {
                isLoading.value = true
                block()
            } catch (error: Exception) {
                error.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }

}