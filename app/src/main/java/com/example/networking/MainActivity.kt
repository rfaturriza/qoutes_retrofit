package com.example.networking

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.networking.ui.theme.NetworkingTheme
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NetworkingTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListQuote()
                }
            }
        }
//        val todosApi = RetrofitHelper.getInstance().create(TodoApi::class.java)
//        GlobalScope.launch {
//            val result = todosApi.getTodos()
//            if (result != null)
//            // Checking the results
//                Log.d("Todos response", result.body().toString())
//        }
    }
}

@Composable
fun ListQuote() {
    val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
    // launching a new coroutine
    val coroutineScope = rememberCoroutineScope()
    val (loadResult, setLoadResult) = remember { mutableStateOf<QuoteList?>(null) }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val result = quotesApi.getQuotes()
            setLoadResult(result.body())
        }
    }
    if (loadResult != null) {
        Column (
            modifier = Modifier.padding(16.dp)
                .verticalScroll(rememberScrollState())

        ) {
            for (quote in loadResult.results) {
                Text(
                    text = quote.author,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = quote.content,
                    fontSize = 15.sp,
                )
                Text(
                    text = quote.dateAdded,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NetworkingTheme {
        ListQuote()
    }
}