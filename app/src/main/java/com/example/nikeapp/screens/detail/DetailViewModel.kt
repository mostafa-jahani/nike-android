package com.example.nikeapp.screens.detail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeapp.network.models.Comment
import com.example.nikeapp.network.models.home.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val repository: DetailRepository) : ViewModel() {

    val productLiveData = MutableLiveData<Product>()
    val commentsLiveData = MutableLiveData<List<Comment>>()


    init {
        productLiveData.value = savedStateHandle["product"]!!
        getComments(productLiveData.value!!.id)
    }


    fun getComments(productId: Int) = viewModelScope.launch {
        val commentResponse = repository.getComments(productId)
        if (commentResponse.isSuccessful) commentsLiveData.postValue(commentResponse.body())
    }





}