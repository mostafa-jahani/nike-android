package com.example.nikeapp.network.models.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product(
    var discount: Int,
    var id: Int,
    var image: String,
    var previous_price: Int,
    var price: Int,
    var status: Int,
    var title: String
) : Parcelable