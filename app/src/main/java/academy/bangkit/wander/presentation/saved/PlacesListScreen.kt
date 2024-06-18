package academy.bangkit.wander.presentation.saved

import academy.bangkit.wander.R
import academy.bangkit.wander.presentation.saved.widgets.Place
import academy.bangkit.wander.presentation.saved.widgets.PlaceCard
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun PlacesListScreenPreview() {
    PlacesListScreen(
        categoryTitle = "Category A",
        navigateBack = {}
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlacesListScreen(
    categoryTitle: String,
    navigateBack: () -> Unit
) {
    // Dummy data for demonstration
    val places = remember {
        listOf(
            Place(1, "Place A", "Location A", R.drawable.location, "Description A", listOf(
                R.drawable.location,
                R.drawable.location,
                R.drawable.location,
                R.drawable.location
            )),
            Place(2, "Place B", "Location B", R.drawable.location, "Description A",listOf(
                R.drawable.location,
                R.drawable.location,
                R.drawable.location,
                R.drawable.location
            )),
            Place(3, "Place C", "Location C", R.drawable.location, "Description A",listOf(
                R.drawable.location,
                R.drawable.location,
                R.drawable.location,
                R.drawable.location
            )),
            Place(4, "Place D", "Location D", R.drawable.location, "Description A",listOf(
                R.drawable.location,
                R.drawable.location,
                R.drawable.location,
                R.drawable.location
            )),
            Place(5, "Place E", "Location E", R.drawable.location, "Description A",listOf(
                R.drawable.location,
                R.drawable.location,
                R.drawable.location,
                R.drawable.location
            )),
            Place(6, "Place F", "Location F", R.drawable.location, "Description A",listOf(
                R.drawable.location,
                R.drawable.location,
                R.drawable.location,
                R.drawable.location
            )),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navigateBack() }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = categoryTitle, fontWeight = FontWeight.Bold, fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(places.windowed(size = 2, step = 2, partialWindows = true)) { pairOfPlaces ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (place in pairOfPlaces) {
                        PlaceCard(place = place)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}