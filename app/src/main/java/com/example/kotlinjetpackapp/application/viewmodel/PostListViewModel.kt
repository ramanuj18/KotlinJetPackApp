package com.example.kotlinjetpackapp.application.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinjetpackapp.application.model.Post
import com.example.kotlinjetpackapp.application.model.repository.PostListRepo
import com.example.kotlinjetpackapp.application.views.adapter.PostListAdapter
import com.example.kotlinjetpackapp.utility.AppUtility
import com.example.kotlinjetpackapp.utility.singleArgViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
class PostListViewModel(private val postListRepo: PostListRepo) : ViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PostListViewModel)
    }

    private var mutableLiveData: LiveData<List<Post>>? = null
    private var isLoading=MutableLiveData<Boolean>(false)
    var errorMessage=MutableLiveData<String?>()

    fun getAllPosts() {
        launchDataLoad {
            postListRepo.getAllPosts()
        }
    }

    fun addPost(post: Post){
        launchDataLoad {
            postListRepo.addPost(post)
        }
    }

    fun getAllPostsFromLocalDb(): LiveData<List<Post>>? {
        mutableLiveData = postListRepo.getAllPostsFromLocalDb()
        return mutableLiveData
    }

    fun getIsLoading():LiveData<Boolean>{
        return isLoading
    }

    fun updatePost(post: Post){
       launchDataLoad {
            postListRepo.updatePost(post)
        }
    }

    fun deletePost(id: String){
        launchDataLoad {
            postListRepo.deletePost(id)
        }
    }

    fun errorMessageShown(){
        errorMessage.value=null
    }

    @BindingAdapter("data")
    fun setRecyclerViewProperties(recyclerView:RecyclerView,list:List<Post>){
        if(recyclerView.adapter  is PostListAdapter){
            (recyclerView.adapter as PostListAdapter).setData(list)
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Unit {
        if(!AppUtility.isInternetConnected()){
            errorMessage.value="Please check your internet connection"
            return
        }

        viewModelScope.launch {
            try {
                isLoading.value = true
                block()
            }catch (http: HttpException){
                errorMessage.value="Error code : ${http.code()} ${http.message()}"
            } catch (error: Exception) {
                error.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }

}