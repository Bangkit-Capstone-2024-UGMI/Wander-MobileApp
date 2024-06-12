package academy.bangkit.wander.presentation.profile

import academy.bangkit.wander.R
import academy.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Profile")
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.size(15.dp))
            ProfileCard()
            SettingCard("Settings")
            SettingCard("Activity History")

        }
    }
}

@Composable
fun ProfileCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 142.dp)
            .padding(8.dp) // Optional padding for aesthetics
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.width(16.dp))
            // Image Container
            Box(
                modifier = Modifier
                    .size(100.dp) // Size for the circular image container
                    .clip(CircleShape)
                    .background(Color.Gray) // Placeholder color or background if needed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize() // Ensures the image fits within the Box
                        .clip(CircleShape)
                )
            }

            // Spacer for gap between image and content
            Spacer(modifier = Modifier.width(16.dp))

            // Additional content like name, description, etc.
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = "Antonius Teddy", fontSize = 22.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "antonius@gmail.com", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SettingCard(string: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 60.dp)
            .padding(8.dp)
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Text for Settings
            Text(
                text = string,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            // Spacer to push the icon to the end
            Spacer(modifier = Modifier.weight(1f))

            // Icon at the end of the row
            Image(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = null,
                modifier = Modifier.size(24.dp) // Optional size adjustment for the icon
            )
        }
    }
}

@Composable
fun ActivityCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 142.dp)
            .padding(8.dp) // Optional padding for aesthetics
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            Icon(painter = painterResource(id = R.drawable.history), contentDescription = "history")

            // Spacer for gap between image and content
            Spacer(modifier = Modifier.width(16.dp))

            // Additional content like name, description, etc.
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = "Antonius Teddy", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview
@Composable
fun ProfileSettingScreen() {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back), // Replace with your back arrow drawable resource
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {

                        }
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Settings",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            SettingCard("Name")
            Spacer(modifier = Modifier.height(8.dp)) // Optional spacing between cards
            SettingCard("Email")
        }
    }
}

@Preview
@Composable
fun ProfileActivityScreen() {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back), // Replace with your back arrow drawable resource
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {

                        }
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Activity Name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            ActivityCard()
        }



    }
}


