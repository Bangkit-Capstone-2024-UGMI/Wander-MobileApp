package com.bangkit.wander.presentation.search.widgets

data class PlaceDetails(
    val id: Int,
    val name: String,
    val location: String,
    val imageResource: Int,
    val description: String,
    val moreImages: List<Int>
)
