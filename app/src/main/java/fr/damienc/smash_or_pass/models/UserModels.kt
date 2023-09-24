package fr.damienc.smash_or_pass.models

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("user")
    suspend fun getUser(@Query("username") username: String, @Query("password") password: String): Response<UserResponse>

    @POST("user")
    suspend fun createUser(@Body userData: UserData): Response<UserResponse>
}
data class UserResponse(val error: Boolean, val message: String, val data: UserToken?)

data class UserToken(val token: String)

data class UserData(val name: String, val mail : String, val password : String)