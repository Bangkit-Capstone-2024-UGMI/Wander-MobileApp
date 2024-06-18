package com.bangkit.wander.presentation.search.maps


import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.bangkit.wander.presentation.ViewModelFactory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.currentCameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(state: MapState) {
    val uttara = LatLng(-7.758196581853439, 110.38153731512409)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uttara, 10f)
    }
    val mapProperties = MapProperties(
        isMyLocationEnabled = state.lastKnownLocation != null,
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = LatLng(-7.758196581853439, 110.38153731512409)),
                title = "Uttara",
                draggable = true
            )
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