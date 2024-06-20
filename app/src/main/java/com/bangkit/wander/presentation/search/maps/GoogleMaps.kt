package com.bangkit.wander.presentation.search.maps


import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    targetLatLng: LatLng? = null
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val uttara = LatLng(-7.758196581853439, 110.38153731512409)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uttara, 10f)
    }

    val locationPermissions = rememberMultiplePermissionsState(
        permissions =
        listOf(
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION"
        ),
    )

    LaunchedEffect(key1 = locationPermissions.permissions){
        locationPermissions.launchMultiplePermissionRequest()
        if (locationPermissions.allPermissionsGranted) {
            try {
                val location = fusedLocationClient.lastLocation.await()
                location?.let {
                    cameraPositionState.centerOnLocation(it)
                }
            } catch (e: SecurityException) {
                // Handle exception (e.g., permission was denied)
            }
        }
    }

    val mapProperties = MapProperties(
        isMyLocationEnabled = locationPermissions.allPermissionsGranted,
        maxZoomPreference = 18f,
        minZoomPreference = 5f,
        isBuildingEnabled = true
    )

    val uiSettings = MapUiSettings(
        compassEnabled = true,
        myLocationButtonEnabled = true,
        rotationGesturesEnabled = true,
        tiltGesturesEnabled = true,
        scrollGesturesEnabled = true
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings
        ) {
            targetLatLng?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Selected Location",
                    draggable = true
                )
                // Center the map on the target location
                LaunchedEffect(it) {
                    cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(it, 15f))
                }
            }
        }
    }
}


private suspend fun CameraPositionState.centerOnLocation(
    location: Location
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        LatLng(location.latitude, location.longitude),
        15f
    ),
)