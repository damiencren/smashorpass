package fr.damienc.smash_or_pass.models

import android.content.ContentValues.TAG
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import java.lang.Character.getType
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import javax.net.ssl.HttpsURLConnection

private val url : String = "https://smash.antoinejosset.fr/user";

object UserManager {
    private var responseString: String = ""

    suspend fun createUser(name: String, mail: String, password: String): String = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val mediaType = "application/json".toMediaTypeOrNull()

        val objectMapper = jacksonObjectMapper()
        val user: User = User(name, mail, password)
        //Log.d(TAG, "Request body: " + objectMapper.writeValueAsString(user))
        val jsonString = objectMapper.writeValueAsString(user)
        val requestBody = RequestBody.create(mediaType,jsonString)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        Log.d(TAG, "Request body: " + objectMapper.writeValueAsString(user))


        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            response.body?.string()?.let { responseBody ->
                responseString = responseBody
            }
        }
        responseString
    }

    suspend fun getUser(username: String, password: String): String = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val mediaType = "application/json".toMediaTypeOrNull()

        val objectMapper = jacksonObjectMapper()
        val requestBody = objectMapper.writeValueAsString(mapOf("username" to username, "password" to password))

        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create(mediaType, requestBody))
            .addHeader("Content-Type", "application/json")
            .build()

        Log.d(TAG, "Request body: $requestBody")

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            response.body?.string()?.let { responseBody ->
                return@withContext responseBody
            }
        }
        return@withContext ""
    }
}
