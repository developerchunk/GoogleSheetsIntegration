package com.example.googlesheetsintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.googlesheetsintegration.data.ProductResponse
import com.example.googlesheetsintegration.data.ProductService
import com.example.googlesheetsintegration.ui.theme.GoogleSheetsIntegrationTheme
import com.example.googlesheetsintegration.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {

    private val service = ProductService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val products = produceState(
                initialValue = ProductResponse(data = emptyList()),
                producer = {
                    value = service.getProducts()
                }
            )

            GoogleSheetsIntegrationTheme {

                LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 30.dp)) {

                    items(products.value.data) { product ->

                        Card(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = PurpleGrey80
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 20.dp,
                            )
                        ) {

                            Column(
                                Modifier.padding(10.dp)
                            ) {
                                Text(text = "Product: ${product.productName}")
                                Text(text = "Company: ${product.companyName}")
                                Text(text = "Country: ${product.country}")
                                Text(text = "Description: ${product.productDetails}")
                            }

                        }

                    }

                }


            }
        }
    }
}
