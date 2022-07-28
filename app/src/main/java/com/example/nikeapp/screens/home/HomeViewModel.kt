package com.example.nikeapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeapp.network.models.home.Product
import com.example.nikeapp.network.models.home.Slider
import com.example.nikeapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val sliderLiveData = MutableLiveData<List<Slider>>()
    val latestProductLiveData = MutableLiveData<List<Product>>()
    val popularProductLiveData = MutableLiveData<List<Product>>()


    init {
        loadSliders()
        loadLatestProducts()
        loadPopularProducts()
    }

    private fun loadSliders() = viewModelScope.launch {
        val response = repository.getSliders()
        if (response.isSuccessful) sliderLiveData.postValue(response.body())
    }

    private fun loadLatestProducts() = viewModelScope.launch {
        val response = repository.getLatestProducts(Constants.SORT_LATEST)
        if (response.isSuccessful) latestProductLiveData.postValue(response.body())
    }

    private fun loadPopularProducts() = viewModelScope.launch {
        val response = repository.getLatestProducts(Constants.SORT_POPULAR)
        if (response.isSuccessful) popularProductLiveData.postValue(response.body())
    }

}