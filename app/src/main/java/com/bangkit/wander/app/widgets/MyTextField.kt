package com.bangkit.wander.app.widgets

import androidx.compose.foundation.clickable
import com.bangkit.wander.app.theme.AppColor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    label : String? = null,
    placeholder: String? = null,
    value : String,
    onValueChange : (String) -> Unit = {},
    icon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    isPlanField: Boolean = false,
    onCloseField: () -> Unit = {},
    onClick: (() -> Unit)? = null
) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        if (label != null) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColor.PrimaryDark
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        OutlinedTextField(
            enabled = !readOnly,
            readOnly = readOnly,
            shape = CircleShape,
            modifier = if (onClick == null) Modifier.fillMaxWidth() else Modifier.fillMaxWidth().clickable(onClick = onClick),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = AppColor.PrimaryDark,
                disabledBorderColor = AppColor.PrimaryDark,
                disabledPlaceholderColor = AppColor.PrimaryDarkVariant,
                disabledLeadingIconColor = AppColor.PrimaryDark,
            ),
            placeholder = {
                Text(
                    text = placeholder?:"",
                    style = TextStyle(color = AppColor.PrimaryDarkVariant)
                )
            },
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
            }
        )
    }
}