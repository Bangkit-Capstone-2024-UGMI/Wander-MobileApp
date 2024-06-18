package com.bangkit.wander.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plan(
    val id: String,
    val name: String,
    val location: String,
    val image: String,
    val date: String
) : Parcelable
