package com.fhzapps.listify.presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fhzapps.listify.domain.ListItem
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
                    viewModel.addItem(ListItem(name = name, description = description, isChecked = false))
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
    var name by remember { mutableStateOf(item.name) }
    var description by remember { mutableStateOf(item.description) }
    Dialog(
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column {
            TextField(
                value = name,
                onValueChange = { name = it },
                shape = RoundedCornerShape(2.dp),
                label = { Text("Item Name") })

            TextField(
                value = description,
                onValueChange = { description = it },
                shape = RoundedCornerShape(2.dp),
                label = { Text("Item Description") })

            Row (horizontalArrangement = Arrangement.SpaceEvenly){
                TextButton(
                    colors = goodButton(),
                    onClick = {
                        viewModel.updateItem(item, ListItem(name = name, description = description, isChecked = item.isChecked))
                        onDismiss.invoke()
                    },
                    shape = RoundedCornerShape(2.dp)
                ) {
                    Text("Update Item")
                }

                TextButton(
                    colors = badButton(),
                    shape = RoundedCornerShape(2.dp),
                    onClick = {
                        viewModel.removeItem(item)
                        onDismiss.invoke()
                    }
                ) {
                    Text("Delete Item")
                }
            }
        }
    }

}