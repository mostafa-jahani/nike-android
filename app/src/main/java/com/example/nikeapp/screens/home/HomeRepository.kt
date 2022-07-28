package com.example.nikeapp.screens.home

import com.example.nikeapp.network.ApiService
import javax.inject.Inject


class HomeRepository @Inject constructor(private val api: ApiService) {

    suspend fun getSliders() = api.getSliders()

    suspend fun getLatestProducts(sort: Int) = api.getLatestProducts(sort)

}