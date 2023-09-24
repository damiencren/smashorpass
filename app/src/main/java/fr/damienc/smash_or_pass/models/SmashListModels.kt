package fr.damienc.smash_or_pass.models

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SmashListService {
    @POST("smashlist")
    suspend fun createSmashList(@Body userData: SmashListData): Response<SmashListResponse>

    @GET("smashlist")
    suspend fun getSmashList(@Query("name") name: String): Response<SmashListResponse>
}

data class SmashListResponse(val error: Boolean, val message: String,val data: List<SmashListData>?)

data class SmashListData(val name:String, val description:String, val category: String, val img:String)

