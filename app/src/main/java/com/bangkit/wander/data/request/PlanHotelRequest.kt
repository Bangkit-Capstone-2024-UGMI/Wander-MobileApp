package com.bangkit.wander.data.request

import com.google.gson.annotations.SerializedName

data class PlanHotelRequest (
    val name: String,

    @field:SerializedName("formatted_address")
    val formattedAddress: String,

    val distance: Double,
    val rating: Double,

    @field:SerializedName("predicted_ranking_score")
    val predictedRankingScore: Double,

    @field:SerializedName("place_id")
    val placeId: String
)