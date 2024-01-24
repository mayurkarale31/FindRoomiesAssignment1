package com.bitcodetech.findroomies.myposts.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.findroomies.myposts.models.MyPost
import com.bitcodetech.findroomies.myposts.repository.MyPostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPostViewModel(
    private val myPostRepository: MyPostRepository
) : ViewModel() {

    val myPostsMutableLiveData = MutableLiveData<Boolean>()
    val myPost = ArrayList<MyPost>()

    fun fetchPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            val myPost = myPostRepository.fetchPosts()

            withContext(Dispatchers.Main) {
                this@MyPostViewModel.myPost.addAll(myPost)
                myPostsMutableLiveData.postValue(true)
            }
        }
    }
}