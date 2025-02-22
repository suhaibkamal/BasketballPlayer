package com.petraride.data.remote

import com.google.gson.Gson
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerByIdResponse
import com.petraride.domain.model.PlayerResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.Url
import io.ktor.serialization.gson.*

class PlayersApis {
    val API_KEY = "126cda7b-eb57-41fa-9f68-b8febc0ed3f3"
    val PER_PAGE = "25"
    private val client = HttpClient(Android) {

       install(ContentNegotiation){
           gson {
               serializeNulls()
           }
       }
        install(Logging) {
            level = LogLevel.BODY
            logger = Logger.DEFAULT
        }
    }

    suspend fun getPlayers(cursor:Int?=0): PlayerResponse {
        var response :String =client.get ("https://api.balldontlie.io/v1/players"){
            url{
                parameters.append("cursor", cursor.toString())
                parameters.append("per_page", PER_PAGE)
            }
            headers {
                append("Authorization",API_KEY)
            }
        }.body()
        return Gson().fromJson(response, PlayerResponse::class.java)
    }

    suspend fun getPlayerByID(id:Int):PlayerByIdResponse{
        val response :String= client.get ("https://api.balldontlie.io/v1/players/$id"){
            headers {
                append("Authorization",API_KEY)
            }

        }.body()
        return Gson().fromJson(response, PlayerByIdResponse::class.java)
    }
}