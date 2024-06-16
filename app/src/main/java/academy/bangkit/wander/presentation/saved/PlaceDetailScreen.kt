package academy.bangkit.wander.presentation.saved

import academy.bangkit.wander.R
import academy.bangkit.wander.presentation.saved.widgets.Place
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview (showBackground = true)
@Composable
fun PlaceDetailScreenPreview() {
    val place = Place(
        id = 1,
        name = "Sample Place",
        location = "Sample Location",
        imageResource = R.drawable.location,
        description = "This is a sample description of the place.",
        moreImages = listOf(
            R.drawable.location,
            R.drawable.location,
            R.drawable.location,
            R.drawable.location
        )
    )


    PlaceDetailScreen(place = place, navigateBack = { /* No action in preview */ })

}

@Composable
fun PlaceDetailScreen(place: Place, navigateBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header image with back button
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Ensure the image spans the full width
                    .height(250.dp) // Adjust the height as needed
            ) {
                Image(
                    painter = painterResource(id = place.imageResource),
                    contentDescription = place.name,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Overlay for back arrow
                IconButton(
                    onClick = { navigateBack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                        .align(Alignment.TopStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }

        // Padding for the content below the image
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = place.name, fontSize = 32.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = place.location, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = place.description, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))

                // Buttons for directions
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Adjust spacing between buttons as needed
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { /* Handle directions click */ },
                        modifier = Modifier
                            .height(50.dp)
                            .width(140.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Directions")
                    }
                    OutlinedButton(
                        onClick = { /* Handle start directions click */ },
                        modifier = Modifier
                            .width(90.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Start")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // List of images
        items(place.moreImages.chunked(2)) { rowImages ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                for (imageRes in rowImages) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(182.dp)
                            .height(126.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Adjust vertical spacing between rows
        }
    }
}