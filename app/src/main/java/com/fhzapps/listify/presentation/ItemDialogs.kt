package com.fhzapps.listify.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fhzapps.listify.data.ListItem
import com.fhzapps.listify.domain.ListViewModel
import com.fhzapps.listify.ui.theme.badButton
import com.fhzapps.listify.ui.theme.goodButton

@Composable
fun AddItemDialog(
    viewModel: ListViewModel = viewModel(),
    onDismiss: () -> Unit,
) {

    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card (
            shape = RoundedCornerShape(8.dp)
        ){
            Text("Add Item Details")
            TextField(
                value = name,
                onValueChange = {name = it},
                label = { Text("Item Name") }
            )
            TextField(
                value = description,
                onValueChange = { description = it},
                label = { Text("Item Description") }
            )
            TextButton(
                onClick = {
                    viewModel.addItem(ListItem(name, description, false))
                    onDismiss.invoke()
                }
            ) {
                Text("Add to List")
            }
        }
    }
}

@Composable
fun UpdateItemDialog(
    item: ListItem,
    viewModel: ListViewModel = viewModel(),
    onDismiss: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf(item.name) }
    var description by rememberSaveable { mutableStateOf(item.description) }
    Dialog(
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column {
            TextField(value = name, onValueChange = { name = it })
            TextField(value = description, onValueChange = { description = it })
            Row {
                TextButton(
                    colors = goodButton(),
                    onClick = {
                        viewModel.updateItem(ListItem(name, description, item.isChecked))
                        onDismiss.invoke()
                    }) {
                    Text("Update Item")
                }
                TextButton(
                    colors = badButton(),
                    onClick = {
                        viewModel.removeItem(item)
                        onDismiss.invoke()
                    }) {
                    Text("Delete Item")
                }
            }
        }
    }

}