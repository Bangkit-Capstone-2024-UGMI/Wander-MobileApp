package com.bangkit.wander.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class HotelsRequest(
    @field:SerializedName("user_id")
    val userId: Int = 123,
    @field:SerializedName("top_n")
    val topN: Int = 10,
    @field:SerializedName("tour_interests")
    val tourInterests : List<String>
) : Parcelable