package com.example.nikeapp.screens.detail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nikeapp.network.models.home.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    val productLiveData = MutableLiveData<Product>()

    init {
        productLiveData.value = savedStateHandle["product"]!!
    }





}