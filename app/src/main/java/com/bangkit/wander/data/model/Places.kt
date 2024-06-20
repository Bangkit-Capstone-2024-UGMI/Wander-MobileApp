package com.bangkit.wander.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Places(
    val placeImage: Int,
    val placeTitle: String,
    val placeLocation: String
) : Parcelable
