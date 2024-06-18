package com.bangkit.wander.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Destination (
    val name: String,
    val location: Location
) : Parcelable

@Parcelize
data class Location (
    val latitude: Double,
    val longitude: Double
) : Parcelable
