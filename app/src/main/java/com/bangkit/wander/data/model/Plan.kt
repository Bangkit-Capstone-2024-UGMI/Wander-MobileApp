package com.bangkit.wander.data.model

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class Plan (
    val id: String,

    @SerialName("userId")
    val userID: Long,

    val title: String,
    val date: String,
    val city: String,
    val destinations: List<Destination>,
    val hotel: Hotel
)

