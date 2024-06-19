package com.bangkit.wander.data.remote

import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.request.HotelsRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface HotelService {

    @POST("rank_hotels/")
    suspend fun findHotels(@Body request: HotelsRequest): List<Hotel>
}