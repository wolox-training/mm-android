package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.RequestLike
import ar.com.wolox.android.example.model.RequestNews
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface NewsService {

    @GET("/comments")
    suspend fun getNews(@Query("page") num : Int): Response<RequestNews>

    @PUT("/comments/like/{id}")
    suspend fun setLike(@Path("id") id : String): Response<RequestLike>

    @GET("/comments/{id}")
    suspend fun getDataNew(@Path("id") id : String): Response<News>
}
