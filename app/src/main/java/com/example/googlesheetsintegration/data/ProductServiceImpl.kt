package com.example.googlesheetsintegration.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class ProductServiceImpl(
    private val client: HttpClient
): ProductService {
    override suspend fun getProducts(): ProductResponse {

        return try {
            client.get { url(HttpRoute.URL) }
        } catch(e: Exception) {
            ProductResponse(data = emptyList())
        }

    }
}