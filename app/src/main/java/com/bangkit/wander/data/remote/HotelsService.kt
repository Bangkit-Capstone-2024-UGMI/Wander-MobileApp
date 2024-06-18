package com.bangkit.wander.data.remote

import androidx.lifecycle.LiveData
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.request.HotelsRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface HotelsService {

    @POST("rank_hotels/")
    suspend fun findHotels(@Body request: HotelsRequest): List<Hotel>
}