package com.bangkit.wander.app.widgets

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.wander.app.theme.AppColor
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.Arrays

@SuppressLint("UnrememberedMutableState")
@Composable
fun DestinationTextField(
    label: String? = null,
    placeholder: String? = null,
    value: String,
    onValueChange: (String) -> Unit = {},
    icon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    isPlanField: Boolean = false,
    onCloseField: () -> Unit = {},
    onClick: (() -> Unit)? = null,
    onPlaceSelected: (Place) -> Unit = {}
){
    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }
    var predictionsState by remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }

    Column(modifier = Modifier.fillMaxWidth()) {
        if (label != null) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Update with your color
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        OutlinedTextField(
            shape = CircleShape,
            value = value,
            onValueChange = {
                onValueChange(it)
                if (it.isNotEmpty()) {
                    fetchAutocompletePredictions(it, placesClient) { predictions ->
                        predictionsState = predictions
                    }
                } else {
                    predictionsState = emptyList()
                }
            },
            modifier = if (onClick == null) Modifier.fillMaxWidth() else Modifier.fillMaxWidth().clickable(onClick = onClick),
            readOnly = readOnly,
            placeholder = { Text(text = placeholder ?: "", color = Color.Gray) },
            leadingIcon = icon,
            trailingIcon = {
                if (isPlanField) {
                    IconButton(onClick = onCloseField) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = AppColor.Error
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle done action if needed
                }
            )
        )
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                predictionsState.forEach { prediction ->
                    Text(
                        text = prediction.getFullText(null).toString(),
                        modifier = Modifier.clickable {
                            fetchPlaceDetails(prediction.placeId, placesClient) { place ->
                                onPlaceSelected(place)
                                predictionsState = emptyList() // Clear predictionsState to hide dropdown after selecting a place
                            }
                        }.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = TextStyle(color = Color.Black),

                    )
                    Divider(color = Color.LightGray, thickness = 1.dp)
                }
            }
        }
    }
}

fun fetchAutocompletePredictions(query: String, placesClient: PlacesClient, onComplete: (List<AutocompletePrediction>) -> Unit) {
    val autocompleteRequest = AutocompleteSessionToken.newInstance()
    val request = FindAutocompletePredictionsRequest.builder()
        .setSessionToken(autocompleteRequest)
        .setQuery(query)
        .build()

    placesClient.findAutocompletePredictions(request)
        .addOnSuccessListener { response ->
            onComplete(response.autocompletePredictions)
        }
        .addOnFailureListener { exception ->
            Log.e("Places", "Failed to fetch autocomplete predictions: ${exception.message}")
            onComplete(emptyList())
        }
}

private fun fetchPlaceDetails(placeId: String, placesClient: PlacesClient, callback: (Place) -> Unit) {
    val placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
    val request = FetchPlaceRequest.builder(placeId, placeFields).build()
    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            callback(response.place)
        }
        .addOnFailureListener { exception ->
            // Handle failure
        }
}