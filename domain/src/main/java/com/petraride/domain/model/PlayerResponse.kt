package com.petraride.domain.model

import android.graphics.Color
import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("data") val players: List<Player>,
    @SerializedName("meta") val meta: Meta
)

data class Player(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("position") val position: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("jersey_number") val jerseyNumber: String,
    @SerializedName("college") val college: String?, // Can be null
    @SerializedName("country") val country: String,
    @SerializedName("draft_year") val draftYear: Int?, // Can be null
    @SerializedName("draft_round") val draftRound: Int?, // Can be null
    @SerializedName("draft_number") val draftNumber: Int?, // Can be null
    @SerializedName("team") val team: Team
)

data class Team(
    @SerializedName("id") val id: Int,
    @SerializedName("conference") val conference: String,
    @SerializedName("division") val division: String,
    @SerializedName("city") val city: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("abbreviation") val abbreviation: String
)

data class Meta(
    @SerializedName("next_cursor") val nextCursor: Int,
    @SerializedName("prev_cursor") val prevCursor: Int,
    @SerializedName("per_page") val perPage: Int
)

data class PlayerByIdResponse(
    @SerializedName("data") val player: Player,
)

fun Player.color(): Long{
    when(position){
        "G-F"->{
            return 0xFF4CAF50
        }
        "C"->{
            return 0xFFFF9800
        }
        "F" ->{
            return 0xFF9C27B0
        }
        "G"->{
            return 0xFFF44336
        }
    }
    return 0xFF2196F3
}

fun Player.image(): String{
    return "https://cdn2.thecatapi.com/images/${id}.jpg"
}