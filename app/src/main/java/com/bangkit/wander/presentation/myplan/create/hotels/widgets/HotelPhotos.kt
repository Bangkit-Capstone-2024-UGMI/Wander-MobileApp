package com.bangkit.wander.presentation.myplan.create.hotels.widgets

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@Composable
fun PlacePhotosWidget(placeId: String) {
    val context = LocalContext.current
    var photoBitmaps by remember { mutableStateOf<List<Bitmap>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(placeId) {
        try {
            val photos = fetchPlacePhotos(context, placeId)
            val bitmaps = photos.mapNotNull { metadata ->
                getPhotoBitmap(context, metadata)
            }
            photoBitmaps = bitmaps
            isLoading = false
        } catch (e: Exception) {
            Log.e("PlacePhotosWidget", "Error fetching photos: ${e.message}")
            errorMessage = "Failed to load photos"
            isLoading = false
        }
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else if (errorMessage != null) {
        Text(
            text = errorMessage ?: "Unknown error",
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(photoBitmaps) { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Place Photo",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

suspend fun fetchPlacePhotos(context: Context, placeId: String): List<PhotoMetadata> {
    return withContext(Dispatchers.IO) {
        val placesClient: PlacesClient = Places.createClient(context)
        val placeFields = listOf(Place.Field.PHOTO_METADATAS)
        val request = FetchPlaceRequest.builder(placeId, placeFields).build()

        val response = placesClient.fetchPlace(request).await()
        response.place.photoMetadatas ?: emptyList()
    }
}

suspend fun getPhotoBitmap(context: Context, photoMetadata: PhotoMetadata): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val placesClient: PlacesClient = Places.createClient(context)
            val photoRequest = FetchPhotoRequest.builder(photoMetadata)
                .setMaxWidth(600)  // Adjust size as necessary
                .build()

            val response = placesClient.fetchPhoto(photoRequest).await()
            response.bitmap
        } catch (e: Exception) {
            Log.e("getPhotoBitmap", "Error fetching photo: ${e.message}")
            null
        }
    }
}