package com.bangkit.wander.presentation.saved

import com.bangkit.wander.R
import com.bangkit.wander.presentation.saved.widgets.PlaceData
import com.bangkit.wander.presentation.saved.widgets.PlaceCard
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bangkit.wander.app.navigation.AppRoute


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlacesListScreen(
    navController: NavHostController
) {

    val samplePlaces = listOf(
        PlaceData("Hotel Artotel", "Yogyakarta", R.drawable.location2),
        PlaceData("Hotel MM UGM", "Yogyakarta", R.drawable.location3),
        PlaceData("Hotel Porta", "Yogyakarta", R.drawable.location4),
        PlaceData("Hotel Tentrem", "Yogyakarta", R.drawable.location5)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Category Details", fontWeight = FontWeight.Bold, fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(samplePlaces) { place ->
                PlaceCard(
                    place = place,
                    onClick = { navController.navigate(AppRoute.PLACE_DETAIL) }
                )
            }

        }
    }
}