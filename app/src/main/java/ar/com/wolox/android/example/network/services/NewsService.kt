package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.RequestNews
import retrofit2.Response
import retrofit2.http.*

interface NewsService {

    @GET("/comments")
    suspend fun getNews(@Query("page") num : Int): Response<RequestNews>
}
