package fr.damienc.smash_or_pass.utils

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.damienc.smash_or_pass.models.SmashListData
import fr.damienc.smash_or_pass.models.SmashListResponse
import fr.damienc.smash_or_pass.models.SmashListService
import fr.damienc.smash_or_pass.models.UserData
import fr.damienc.smash_or_pass.models.UserResponse
import fr.damienc.smash_or_pass.models.UserService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import java.net.URL

object SmashListManager {
    private const val BASE_URL = "https://smash.antoinejosset.fr/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val smashListService = retrofit.create(SmashListService::class.java)

    suspend fun createSmashList(name: String, description: String, category: String, img : String): SmashListResponse? {
        val smashListData = SmashListData(name, description, category, img)
        val response = smashListService.createSmashList(smashListData)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getSmashList(name: String): SmashListResponse? {
        val response = smashListService.getSmashList(name)
        Log.d(ContentValues.TAG, response.toString())
        return if (response.isSuccessful) {

            response.body()
        } else {
            null
        }
    }
}