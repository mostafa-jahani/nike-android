package com.example.nikeapp.screens.detail

import com.example.nikeapp.network.ApiService
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiService){

    suspend fun getComments(productId: Int)  = api.getComments(productId)
}