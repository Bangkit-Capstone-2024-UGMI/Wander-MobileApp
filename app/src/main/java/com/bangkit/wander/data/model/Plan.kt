package com.bangkit.wander.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plan (
    val id: String,
    val userId: Long,
    val title: String,
    val date: String,
    val city: String,
    val destinations: List<Destination>,
    val hotel: Hotel
) : Parcelable

