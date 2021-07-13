package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.User
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/auth/sign_in")
    suspend fun signIn(@Body requestBody: RequestBody): Response<User>
}
