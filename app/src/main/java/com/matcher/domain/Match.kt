package com.matcher.domain

data class Match (
    val description: String,
    val place: Place,
    val firstTeam: Team,
    val secondTeam: Team
)