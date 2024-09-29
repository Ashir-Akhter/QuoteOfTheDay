package com.example.quoteoftheday // Update this to match your project name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quoteoftheday.ui.theme.QuoteOfTheDayTheme
import com.example.quoteoftheday.viewmodel.QuoteViewModel // Import the ViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
// import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Alignment
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteOfTheDayTheme {
                // Display the daily quote
                DailyQuoteScreen()
            }
        }
    }
}

@Composable
fun DailyQuoteScreen(quoteViewModel: QuoteViewModel = viewModel()) {
    var quote by remember { mutableStateOf("Loading...") }

    // Fetch the daily quote from the ViewModel
    LaunchedEffect(Unit) {
        quoteViewModel.getDailyQuote { newQuote ->
            quote = newQuote
        }
    }

    // UI for displaying the quote
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = quote, style = MaterialTheme.typography.h5)
        }
    }
}

// Hardcoding the API key for your ViewModel or any data source
const val API_KEY = "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" // Replace with your actual API key
