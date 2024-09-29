package com.example.quoteoftheday.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Model for OpenAI API request
data class OpenAIRequest(
    val model: String,
    val prompt: String,
    val max_tokens: Int,
    val temperature: Double
)

// Model for OpenAI API response
data class OpenAIResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)

interface OpenAIService {

    // Define the endpoint for creating a completion request
    @POST("v1/completions")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer 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" // Your API key here
    )
    suspend fun getQuote(@Body request: OpenAIRequest): OpenAIResponse

    companion object {
        private const val BASE_URL = "https://api.openai.com/"

        // Create Retrofit instance
        fun create(): OpenAIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(OpenAIService::class.java)
        }
    }
}
