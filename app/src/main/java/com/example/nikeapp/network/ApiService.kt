package com.example.nikeapp.network

import com.example.nikeapp.network.models.Comment
import com.example.nikeapp.network.models.home.Product
import com.example.nikeapp.network.models.home.Slider
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("banner/slider")
    suspend fun getSliders() : Response<List<Slider>>

    @GET("product/list")
    suspend fun getLatestProducts(@Query("sort") sort: Int) : Response<List<Product>>

    @GET("comment/list")
    suspend fun getComments(@Query("product_id") productId: Int) : Response<List<Comment>>
}