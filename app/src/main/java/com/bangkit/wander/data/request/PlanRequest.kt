package com.bangkit.wander.data.request

import com.bangkit.wander.data.model.Destination
import com.bangkit.wander.data.model.Hotel
import com.google.gson.annotations.SerializedName

data class PlanRequest (
    val title: String,

    @field:SerializedName("startDate")
    val date: String,

    val city: String,
    val destinations: List<Destination>,

)