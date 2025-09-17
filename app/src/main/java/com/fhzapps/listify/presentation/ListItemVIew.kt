package com.fhzapps.listify.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fhzapps.listify.data.ListItem
import com.fhzapps.listify.domain.ListViewModel

@Composable
fun ListItemView(
    listItem: ListItem,
    modifier: Modifier,
    viewModel: ListViewModel = viewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    Box (
        modifier = Modifier.clickable(true, onClick = { showDialog.value = true })
        )
    {
        Row (
            modifier.fillMaxWidth()
                .background(color = Color.Cyan),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = listItem.name,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Text(text = listItem.description,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Checkbox(
                checked = listItem.isChecked,
                onCheckedChange = { viewModel.toggleItem(listItem) },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

    if (showDialog.value) {
        UpdateItemDialog(listItem) { showDialog.value = false }
    }
}
