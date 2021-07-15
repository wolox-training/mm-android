package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.AuthenticationBody
import ar.com.wolox.android.example.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/auth/sign_in")
    suspend fun signIn(@Body authBody: AuthenticationBody): Response<User>
}
