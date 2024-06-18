package com.bangkit.wander.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hotel (
    val name: String,

    @SerialName("formatted_address")
    val formattedAddress: String,

    val distance: Double,
    val rating: Long,

    @SerialName("predicted_ranking_score")
    val predictedRankingScore: Double,

    val lat: Double,
    val lng: Double,

    @SerialName("place_id")
    val placeID: String
)