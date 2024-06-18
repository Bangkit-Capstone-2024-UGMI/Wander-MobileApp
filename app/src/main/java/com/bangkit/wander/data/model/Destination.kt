package com.bangkit.wander.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Destination (
    val name: String,
    val location: Location
)

@Serializable
data class Location (
    val latitude: Double,
    val longitude: Double
)
