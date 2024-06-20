package com.bangkit.wander.presentation.search

import com.bangkit.wander.presentation.search.widgets.BottomWidgetContent
import com.bangkit.wander.presentation.search.maps.MapScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.presentation.ViewModelFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WanderScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: WanderViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val placeList by viewModel.getPlaceList().observeAsState(initial = emptyList())
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState()
    )
    val scope = rememberCoroutineScope()
    val placesClient: PlacesClient = Places.createClient(context)
    var predictions by remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }
    var selectedLatLng by remember { mutableStateOf<LatLng?>(null)}

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomWidgetContent(
                placeList,
                navController = navController,
                onExpand = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
            )
        },
        sheetPeekHeight = 120.dp,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                MapScreen(selectedLatLng)
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    query = text,
                    onQueryChange = { query ->
                        text = query
                        if (query.isNotEmpty()) {
                            val request = FindAutocompletePredictionsRequest.builder()
                                .setQuery(query)
                                .build()
                            placesClient.findAutocompletePredictions(request)
                                .addOnSuccessListener { response ->
                                    predictions = response.autocompletePredictions
                                }
                                .addOnFailureListener { exception ->
                                    // Handle error
                                }
                        } else {
                            predictions = emptyList()
                        }
                    },
                    onSearch = { active = false },
                    active = active,
                    onActiveChange = { active = it },
                    placeholder = { Text(text = "Where are we wandering today?", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                    }
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                ){
                    Column {
                        predictions.forEach { prediction ->
                            Text(
                                text = prediction.getFullText(null).toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        // Fetch place details when a prediction is clicked
                                        fetchPlaceDetails(prediction.placeId, placesClient) { place ->
                                            selectedPlace = place
                                            selectedLatLng = place.latLng
                                            active = false // Close the search bar
                                            text = place.name ?: ""
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun fetchPlaceDetails(placeId: String, placesClient: PlacesClient, onPlaceFetched: (Place) -> Unit) {
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)
    val placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build()

    placesClient.fetchPlace(placeRequest)
        .addOnSuccessListener { response ->
            val place = response.place
            onPlaceFetched(place)
        }
        .addOnFailureListener { exception ->
            // Handle error
        }
}