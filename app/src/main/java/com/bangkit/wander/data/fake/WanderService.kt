package com.bangkit.wander.data.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.wander.R
import com.bangkit.wander.data.model.Places

class WanderService {

    private val placeList = MutableLiveData<List<Places>>()

    init{
        placeList.value = listOf(
            Places(
                placeImage = R.drawable.location,
                placeTitle = "Hotel Uttara",
                placeLocation = "Yogyakarta"
            ),

            Places(
                placeImage = R.drawable.location,
                placeTitle = "Hotel Uttara",
                placeLocation = "Yogyakarta"
            ),

            Places(
                placeImage = R.drawable.location,
                placeTitle = "Hotel Uttara",
                placeLocation = "Yogyakarta"
            ),

            Places(
                placeImage = R.drawable.location,
                placeTitle = "Hotel Uttara",
                placeLocation = "Yogyakarta"
            ),

            Places(
                placeImage = R.drawable.location,
                placeTitle = "Hotel Uttara",
                placeLocation = "Yogyakarta"
            )
        )
    }

    fun getPlaceList(): LiveData<List<Places>> {
        return placeList
    }

}