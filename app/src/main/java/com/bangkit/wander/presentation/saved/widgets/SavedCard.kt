package com.bangkit.wander.presentation.saved.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.wander.R

@Preview(showBackground = true)
@Composable
fun SavedCardPreview() {
    val sampleItem = SavedCardItem(
        id = 1,
        title = "Sample Place",
        iconResource = R.drawable.star // Replace with an actual drawable resource
    )

    SavedCard(
        item = sampleItem,
        onEdit = { /* Handle edit */ },
        onDelete = { /* Handle delete */ },
        onClick = { /* Handle click */ }
    )
}

@Composable
fun SavedCard(
    item: SavedCardItem,
    onEdit: (SavedCardItem) -> Unit,
    onDelete: (SavedCardItem) -> Unit,
    onClick: (SavedCardItem) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(52.dp)
            .clickable { onClick(item) },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFF4D160)  // Custom card color
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = item.iconResource),
                contentDescription = "${item.title} icon",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = item.title,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Options")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = {
                        showMenu = false
                        onEdit(item)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = {
                        showMenu = false
                        onDelete(item)
                    }
                )
            }
        }
    }
}

