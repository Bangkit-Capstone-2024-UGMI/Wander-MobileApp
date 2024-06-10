package academy.bangkit.wander.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WanderScreen() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState()
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomWidgetContent(
                onExpand = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            )
        },
        sheetPeekHeight = 120.dp,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            Scaffold {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = { text = it },
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
            ) {
                // Additional content for the search bar
            }
        }
    }}
}

@Composable
fun BottomWidgetContent(onExpand: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Latest in the area..",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    onExpand()
                },
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Hotels Nearby",
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardSection()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cafes Nearby",
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardSection()

    }
}