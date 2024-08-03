package com.example.googlesheetsintegration.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val data: List<Product>
)

@Serializable
data class Product(
    val productName: String,
    val companyName: String,
    val country: String,
    val productDetails: String
)