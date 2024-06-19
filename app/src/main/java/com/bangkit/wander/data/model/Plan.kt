package com.bangkit.wander.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plan (
    val id: String? = null,
    val userId: Long? = null,
    val title: String,

    @field:SerializedName("startDate")
    val date: String,

    val city: String,
    val destinations: List<Destination>,
    var hotel: Hotel? = null
) : Parcelable

