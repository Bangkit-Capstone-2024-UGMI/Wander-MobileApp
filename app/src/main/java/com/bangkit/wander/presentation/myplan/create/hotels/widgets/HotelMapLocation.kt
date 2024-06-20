package com.bangkit.wander.presentation.myplan.create.hotels.widgets

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

@Composable
fun HotelLocationMap(placeId: String) {
    val context = LocalContext.current
    var placeLatLng by remember { mutableStateOf<LatLng?>(null) }

    // Fetch place details
    LaunchedEffect(placeId) {
        fetchPlaceDetails(context, placeId) { place ->
            place.latLng?.let {
                placeLatLng = it
            }
        }
    }

    placeLatLng?.let { latLng ->
        ComposeMapView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            onMapReady = { googleMap ->
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                googleMap.addMarker(MarkerOptions().position(latLng).title("Selected Place"))
            }
        )
    }
}

@Composable
fun ComposeMapView(
    modifier: Modifier = Modifier,
    onMapReady: (GoogleMap) -> Unit
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    DisposableEffect(mapView) {
        mapView.onCreate(Bundle())
        mapView.onResume()
        val callback = OnMapReadyCallback { googleMap -> onMapReady(googleMap) }
        mapView.getMapAsync(callback)

        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { mapView }
    )
}

fun fetchPlaceDetails(context: Context, placeId: String, callback: (Place) -> Unit) {
    val placesClient: PlacesClient = Places.createClient(context)
    val placeFields = listOf(Place.Field.LAT_LNG)
    val request = FetchPlaceRequest.builder(placeId, placeFields).build()

    placesClient.fetchPlace(request).addOnSuccessListener { response ->
        val place = response.place
        callback(place)
    }.addOnFailureListener { exception ->
        Log.e("PlaceDetails", "Place not found: ${exception.message}")
    }
}