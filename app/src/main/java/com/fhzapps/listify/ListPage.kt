package com.fhzapps.listify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fhzapps.listify.domain.ListViewModel
import com.fhzapps.listify.domain.ScreenType
import com.fhzapps.listify.presentation.AddItemDialog
import com.fhzapps.listify.presentation.ListItemView
@Composable
fun ShowListPage(
    viewModel: ListViewModel = viewModel()
) {
    val showDialog = remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues = PaddingValues(vertical = 2.dp, horizontal = 2.dp))
            .background(color = Color.Black),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog.value = true
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
       },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomBarSelector() }
    ) { innerPadding ->

        if (viewModel.selectedPage.value == ScreenType.ListPage) {
         ListPage(innerPadding)
        }
        else {
            QuotesPageView(innerPadding)

        }
    }

    if (showDialog.value) {
        AddItemDialog( viewModel ) {
            showDialog.value = false
        }
    }
}

@Composable
fun BottomBarSelector(
    viewModel: ListViewModel = viewModel()
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ){
        IconButton(
            onClick = { viewModel.setScreen(ScreenType.ListPage) }
        ) {
            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Lists")
        }
        IconButton(
            onClick = { viewModel.setScreen(ScreenType.QuotesPage) }
        ) {
            Icon(Icons.Filled.Person, contentDescription = "Kimi Quotes")
        }
    }
}

@Composable
fun ListPage(
    innerPadding: PaddingValues,
    viewModel: ListViewModel = viewModel()

) {
    val checkedItems = viewModel.getCheckedItems().collectAsState(listOf())
    val uncheckedItems = viewModel.getUncheckedItems().collectAsState(listOf())
    val itemCount = viewModel.itemCount().collectAsState(0)
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (itemCount.value <= 0) {
            Text("Add some Items!", fontSize = 24.sp)

        }
        if (itemCount.value > 0) {

            LazyColumn { // when things are NOT crossed off, they go here to the top.
                items(
                    uncheckedItems.value.size,
                    itemContent = { item ->
                        ListItemView(uncheckedItems.value[item], Modifier.padding(innerPadding))
                    }
                )
            }

            HorizontalDivider(thickness = 4.dp)
            Text("Items Crossed Off", fontSize = 24.sp)

            LazyColumn { // when things are NOT crossed off, they go here to the top.
                items(
                    checkedItems.value.size,
                    itemContent = { item ->
                        ListItemView(checkedItems.value[item], Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}

@Composable
fun QuotesPageView(
    innerPadding: PaddingValues,
    viewModel: ListViewModel = viewModel()
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quotes Page", fontSize = 24.sp)
    }
}
