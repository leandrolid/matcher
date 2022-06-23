package com.matcher.domain

import com.google.gson.annotations.SerializedName

data class Match (
    @SerializedName("descricao")
    val description: String,
    @SerializedName("local")
    val place: Place,
    @SerializedName("mandante")
    val firstTeam: Team,
    @SerializedName("visitante")
    val secondTeam: Team
)