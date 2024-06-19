package com.bangkit.wander.data.local

import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.data.model.Plan
import com.bangkit.wander.data.request.HotelsRequest

object TemporaryData {
    var sourceHotel: String? = null
    var hotelsRequest : HotelsRequest? = null
    var hotelDetail: Hotel? = null
    var newPlan: Plan? = null
}