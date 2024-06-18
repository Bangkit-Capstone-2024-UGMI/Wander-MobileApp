package com.bangkit.wander.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Hotel (
    val name: String,

    @field:SerializedName("formatted_address")
    val formattedAddress: String,

    val distance: Double,
    val rating: Double,

    @field:SerializedName("predicted_ranking_score")
    val predictedRankingScore: Double,

    val lat: Double,
    val lng: Double,

    @field:SerializedName("place_id")
    val placeId: String
) : Parcelable