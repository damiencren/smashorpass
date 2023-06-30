package fr.damienc.smash_or_pass.models

import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.net.HttpURLConnection
import java.net.URL

private val url : String = "https://smash.antoinejosset.fr/user";

object SmashListManager {

    fun createSmashList(
        name: String,
        description: String,
        category: String,
        img: String
    ): SmashList {
        val sl = SmashList(name, description, category, img)

        val mapper = jacksonObjectMapper()
        val jsonBody = mapper.writeValueAsString(sl)

        //Log.d("I","eze")

        // Configurer la requête HTTP avec le body JSON
        val conn = URL(url).openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json; utf-8")
        conn.doOutput = true

        // Envoyer la requête avec le body JSON
        val outputBytes = jsonBody.toByteArray(Charsets.UTF_8)
        val outputStream = conn.outputStream
        outputStream.write(outputBytes)
        outputStream.flush()
        outputStream.close()

        // Récupérer la réponse de la requête
        val response = conn.inputStream.bufferedReader().use { it.readText() }
        Log.d("I",response)

        return sl

    }
}