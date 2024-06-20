package com.bangkit.wander.presentation.myplan.home.widgets

import android.util.Log
import com.bangkit.wander.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val placesClient = remember { Places.createClient(context) }
    var predictionsState by remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }
    Box(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x592B2002),
                ambientColor = Color(0x59463005)
            )
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp))
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Ready for some adventure?",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF1D5D9B),
                    fontWeight = FontWeight(700),
                )
            )
            var query by remember { mutableStateOf("") }
            OutlinedTextField(
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF1D5D9B),
                    unfocusedBorderColor = Color(0xFF1D5D9B),
                    cursorColor = Color(0xFF1D5D9B),
                    containerColor = Color(0x211D5D9B),
                ),
                value = query,
                onValueChange = {
                    query = it
                    fetchAutocompletePredictions(it, placesClient) { predictions ->
                        predictionsState = predictions
                    }
                },
                placeholder = { Text(text = "Explore amazing destinations here", style = TextStyle(color = Color(0xCC1D5D9B))) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        tint = Color(0xFF1D5D9B)
                    )
                },
            )
            predictionsState.forEach { prediction ->
                Text(
                    text = prediction.getFullText(null).toString(),
                    style = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .clickable {
                            fetchPlaceDetails(prediction.placeId, placesClient) { place ->
                                // Handle the selected place (e.g., navigate to details screen)
                                Log.d("SearchBox", "Selected place: ${place.name}")
                            }
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(Color.White)
                        .zIndex(1f)
                        .border(1.dp, Color.LightGray, CircleShape)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

fun fetchAutocompletePredictions(query: String, placesClient: PlacesClient, onComplete: (List<AutocompletePrediction>) -> Unit) {
    val autocompleteRequest = FindAutocompletePredictionsRequest.builder()
        .setQuery(query)
        .build()

    placesClient.findAutocompletePredictions(autocompleteRequest)
        .addOnSuccessListener { response ->
            onComplete(response.autocompletePredictions)
        }
        .addOnFailureListener { exception ->
            Log.e("SearchBox", "Failed to fetch autocomplete predictions: ${exception.message}")
            onComplete(emptyList())
        }
}

fun fetchPlaceDetails(placeId: String, placesClient: PlacesClient, onComplete: (Place) -> Unit) {
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

    val request = FetchPlaceRequest.builder(placeId, placeFields)
        .build()

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            val place = response.place
            onComplete(place)
        }
        .addOnFailureListener { exception ->
            Log.e("SearchBox", "Failed to fetch place details: ${exception.message}")
        }
}