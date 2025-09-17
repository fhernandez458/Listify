package com.fhzapps.listify

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fhzapps.listify.data.ListItem
import com.fhzapps.listify.domain.ListViewModel
import com.fhzapps.listify.presentation.AddItemDialog
import com.fhzapps.listify.presentation.ListItemView
import kotlinx.coroutines.flow.asFlow

@Composable
fun ShowListPage(
    viewModel: ListViewModel = viewModel()
) {
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .background(color = Color.Black),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog.value = true
                }
            ) { Icon(Icons.Filled.Add, contentDescription = "Add") }
       },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column {
            LazyColumn (contentPadding = innerPadding){ // when things are NOT crossed off, they go here to the top.
                itemsIndexed(items = viewModel.getUncheckedItems()) { index, item ->
                    ListItemView(item, Modifier.padding(innerPadding))

                }
            }
            if (viewModel.itemCount() > 0) {
                HorizontalDivider(thickness = 4.dp)
                Text("Items Crossed Off")
            }

            LazyColumn (contentPadding = innerPadding){ // when things are crossed off, they go here to the bottom
                itemsIndexed(items = viewModel.getCheckedItems()) { index, item ->
                    ListItemView(item, Modifier.padding(innerPadding))

                }
            }
        }

    }

    if (showDialog.value) {
        AddItemDialog( viewModel ) {
            showDialog.value = false
        }
    }
}
