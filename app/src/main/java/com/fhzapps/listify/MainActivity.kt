package com.fhzapps.listify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fhzapps.listify.ui.theme.ListifyTheme
import androidx.lifecycle.ViewModelProvider
import com.fhzapps.listify.domain.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            ListifyTheme {
                ShowListPage(listViewModel)
            }
        }
    }
}
