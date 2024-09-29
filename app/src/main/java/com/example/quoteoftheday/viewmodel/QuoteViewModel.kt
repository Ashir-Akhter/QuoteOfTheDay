package com.example.quoteoftheday.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteoftheday.network.OpenAIRequest
import com.example.quoteoftheday.network.OpenAIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteViewModel : ViewModel() {

    // Create an instance of OpenAIService
    private val openAIService = OpenAIService.create()

    fun getDailyQuote(onQuoteReceived: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = OpenAIRequest(
                    model = "text-davinci-003",  // You can also use "gpt-3.5-turbo"
                    prompt = "Please provide a positive quote for today.",
                    max_tokens = 50,
                    temperature = 0.7
                )

                // Call the API to fetch the quote
                val response = openAIService.getQuote(request)

                // Send the result back to the main thread
                withContext(Dispatchers.Main) {
                    val quote = response.choices.firstOrNull()?.text?.trim() ?: "No quote available"
                    onQuoteReceived(quote)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onQuoteReceived("Failed to fetch quote.")
                }
            }
        }
    }
}
