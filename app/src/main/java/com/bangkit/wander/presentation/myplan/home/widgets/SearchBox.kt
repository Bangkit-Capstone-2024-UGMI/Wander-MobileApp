package com.bangkit.wander.presentation.myplan.home.widgets

import android.util.Log
import com.bangkit.wander.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.theme.AppColor
import com.bangkit.wander.data.local.TemporaryData
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    navController : NavHostController
) {val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    // State for list of attractions
    val attractions = remember { mutableStateListOf<String>() }

    // State for expanded dropdown
    var dropdownExpanded by remember { mutableStateOf(false) }

    // State for search query
    var searchQuery by remember { mutableStateOf("") }

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

    fun goToCreatePlanScreen(attraction: String) {
        TemporaryData.searchDestination = attraction
        navController.navigate(AppRoute.CREATE_PLAN)
    }

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
            Column(modifier = Modifier.fillMaxWidth()) {
                // Attraction Dropdown
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
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    placeholder = { Text(text = "Explore amazing destinations here", style = TextStyle(color=Color(0xCC1D5D9B))) },
                    trailingIcon = {
                        IconButton(onClick = {
                            submitSearch = true
                            dropdownExpanded = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Search Icon",
                                tint = Color(0xFF1D5D9B)
                            )
                        }

                    },
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
                                    goToCreatePlanScreen(attraction)
                                },
                                text = { Text(text = attraction) }
                            )
                        }
                    }
                }
            }
        }
    }
}