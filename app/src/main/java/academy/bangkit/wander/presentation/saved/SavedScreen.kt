package academy.bangkit.wander.presentation.saved

import academy.bangkit.wander.R
import academy.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SavedScreen() {
    val items = remember {
        mutableStateListOf(
            CardItem(1, "Favorites", R.drawable.star),
            CardItem(2, "Want to Go", R.drawable.calendar), // Replace with actual drawable resource
            CardItem(3, "Travel", R.drawable.plane) // Replace with actual drawable resource
        )
    }

    val showDialog = remember { mutableStateOf(false) }

    // Define available icons
    val availableIcons = remember {
        listOf(
            R.drawable.star,
            R.drawable.calendar,
            R.drawable.plane,
            R.drawable.add
        )
    }

    Scaffold(
        topBar = {
            MyTopAppBar(title = "Saved Places")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            NewListButton {
                showDialog.value = true
            }

            Spacer(modifier = Modifier.size(22.dp))

            CardList(items = items)

            // Pass availableIcons to AddCardDialog
            if (showDialog.value) {
                AddCardDialog(
                    availableIcons = availableIcons,
                    onAdd = { newTitle, selectedIcon ->
                        val newCard = CardItem(
                            id = items.size + 1,
                            title = newTitle,
                            iconResource = selectedIcon
                        )
                        items.add(newCard)
                        showDialog.value = false
                    },
                    onDismiss = {
                        showDialog.value = false
                    }
                )
            }
        }
    }
}


@Composable
fun NewListButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .width(380.dp)
            .height(40.dp)
    ) {
        Text("Create New List")
    }
}

@Composable
fun CardList(items: List<CardItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items) { item ->
            SavedCard(item = item) {
                // Handle card click
                println("Clicked on ${item.title}")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SavedCard(item: CardItem, onClick: (CardItem) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(52.dp)
            .clickable { onClick(item) }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = item.iconResource),
                contentDescription = "${item.title} icon",
                modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically)
            )

            Text(
                text = item.title,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Image(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = "Forward arrow",
                modifier = Modifier.padding(end = 16.dp).align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun AddCardDialog(
    availableIcons: List<Int>,
    onAdd: (String, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var newCardTitle by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf(availableIcons[0]) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Create New List") },
        text = {
            Column {
                TextField(
                    value = newCardTitle,
                    onValueChange = { newCardTitle = it },
                    label = { Text("List Name") }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Select an Icon")
                LazyRow {
                    items(availableIcons) { icon ->
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(4.dp)
                                .clickable {
                                    selectedIcon = icon
                                }
                                .border(
                                    if (selectedIcon == icon) 2.dp else 0.dp,
                                    if (selectedIcon == icon) Color.Blue else Color.Transparent
                                )
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (newCardTitle.isNotBlank()) {
                        onAdd(newCardTitle, selectedIcon)
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}







