package com.bangkit.wander.presentation.myplan.create.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bangkit.wander.app.theme.AppColor
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.SearchByTextRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristAttractionDropdown(
    onSelectedValue: (String) -> Unit = {},
    isSelected: Boolean = false,
    onSearch: (List<String>) -> Unit = {},
    onCloseField: () -> Unit = {},
    value: String = ""
) {
    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    // State for list of attractions
    val attractions = remember { mutableStateListOf<String>() }

    // State for expanded dropdown
    var dropdownExpanded by remember { mutableStateOf(false) }

    // State for search query
    var searchQuery by remember { mutableStateOf(value) }

    var submitSearch by remember { mutableStateOf(false) }

    // Function to fetch places based on text search
    fun searchPlaces(query: String, callback: (List<String>) -> Unit) {

        val autocompleteRequest = AutocompleteSessionToken.newInstance()
        val request = FindAutocompletePredictionsRequest.builder()
            .setSessionToken(autocompleteRequest)
            .setQuery(query)
            .setCountries("ID")
            .setTypesFilter(listOf("tourist_attraction"))
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                val places = response.autocompletePredictions.map {
                    it.getPrimaryText(null).toString()
                }
                Log.d("SEARCH", places.toString())
                callback(places)
                submitSearch = false
            }
            .addOnFailureListener { exception ->
                Log.e("Places", "Search failed", exception)
                callback(emptyList())
                submitSearch = false
            }
    }

    // Fetch attractions based on search query
    LaunchedEffect(searchQuery, submitSearch) {
        if (submitSearch) {
            searchPlaces(searchQuery) { results ->
                attractions.clear()
                attractions.addAll(results)
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Attraction Dropdown
        OutlinedTextField(
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth(),
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = AppColor.PrimaryDark,
                disabledBorderColor = AppColor.PrimaryDark,
                disabledPlaceholderColor = AppColor.PrimaryDarkVariant,
                disabledLeadingIconColor = AppColor.PrimaryDark,
            ),
            placeholder = {
                Text(
                    text = "Find a Destination",
                    style = TextStyle(color = AppColor.PrimaryDarkVariant)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    submitSearch = true
                    dropdownExpanded = true
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                    )
                }
            },

            leadingIcon = {

                if (isSelected) {
                    IconButton(onClick = onCloseField) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = AppColor.Error
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = "Destination"
                    )
                }

            }
        )
        Box(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false }
            ) {
                attractions.forEach { attraction ->
                    DropdownMenuItem(
                        onClick = {
                            searchQuery = attraction
                            dropdownExpanded = false
                            onSelectedValue(attraction)
                        },
                        text = { Text(text = attraction) }
                    )
                }
            }
        }
    }
}
