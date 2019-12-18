package com.example.kotlinjetpackapp.application.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinjetpackapp.R
import com.example.kotlinjetpackapp.apiclient.ApiClient
import com.example.kotlinjetpackapp.application.model.Posts
import com.example.kotlinjetpackapp.application.model.repository.PostListRepo
import com.example.kotlinjetpackapp.application.viewmodel.PostListViewModel
import com.example.kotlinjetpackapp.application.views.adapter.OnRecyclerItemClick
import com.example.kotlinjetpackapp.application.views.adapter.PostListAdapter
import com.example.kotlinjetpackapp.databinding.ActivityPostListBinding
import com.example.kotlinjetpackapp.roomdb.AppDatabase
import com.example.kotlinjetpackapp.sync_adapter.SyncAdapterManager
import com.example.kotlinjetpackapp.utility.ThisApplication

class PostListActivity : AppCompatActivity() ,View.OnClickListener{

    lateinit var syncAdapterManager: SyncAdapterManager

    private lateinit var postListViewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPostListBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        syncAdapterManager= SyncAdapterManager(this)
        val postDao = AppDatabase.invoke().postDao()
        val serviceApi = ApiClient.getServices()
        val postListRepo = PostListRepo(serviceApi, postDao)
        postListViewModel = ViewModelProviders.of(this, PostListViewModel.FACTORY(postListRepo))
            .get(PostListViewModel::class.java)

        postListViewModel.getAllPosts()
        binding.floatingActionButton.setOnClickListener(this@PostListActivity)

        val postAdapter = PostListAdapter(this, object : OnRecyclerItemClick {
            override fun onItemClick(post: Posts) {
                val intent = Intent(this@PostListActivity, PostDetailActivity::class.java)
                intent.putExtra("post", post)
                intent.putExtra("updated",true)
                startActivityForResult(intent, 101)
            }

            override fun onItemLongClick(post: Posts) {
                val builder = AlertDialog.Builder(this@PostListActivity)
                    .setMessage("do you want to delete this post?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialogInterface, which ->
                        postListViewModel.deletePost(post.id.toString())
                        dialogInterface.cancel()
                    }
                    .setNegativeButton("No") { dialogInterface, which ->
                        dialogInterface.cancel()
                    }

                val alert = builder.create()
                alert.show()
            }
        })

        postListViewModel.getAllPostsFromLocalDb()?.observe(this@PostListActivity, object : Observer<List<Posts>> {
            override fun onChanged(t: List<Posts>?) {
                t?.let {
                    postAdapter.setData(it)
                }
            }
        })

        binding.recyclerPostList.adapter = postAdapter
        postListViewModel.getIsLoading().observe(this@PostListActivity, Observer<Boolean> { t ->
            t?.let {
                binding.progressLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        postListViewModel.errorMessage.observe(this@PostListActivity,object:Observer<String?>{
            override fun onChanged(t: String?) {
               t?.let {
                   ThisApplication.showToast(it)
                   postListViewModel.errorMessageShown()
               }
            }
        })
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.floating_action_button->{
                val intent = Intent(this@PostListActivity, PostDetailActivity::class.java)
                intent.putExtra("post", Posts())
                intent.putExtra("updated",false)
                startActivityForResult(intent, 102)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                101 -> {
                    data?.let {
                        val posts = it.getSerializableExtra("updated_post") as Posts
                        postListViewModel.updatePost(posts)
                    }
                }
                102->{
                    data?.let {
                        val posts = it.getSerializableExtra("updated_post") as Posts
                        postListViewModel.addPost(posts)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_refresh->{
            syncAdapterManager.refreshData()
                return true
            }
        }
        return false
    }
}
