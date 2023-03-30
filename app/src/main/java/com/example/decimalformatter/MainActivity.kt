package com.example.decimalformatter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.decimalformatter.ui.theme.DecimalFormatterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DecimalFormatterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val decimalFormatter = DecimalFormatter()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        DecimalInputField(decimalFormatter = decimalFormatter)
                    }
                }
            }
        }
    }
}

@Composable
fun DecimalInputField(
    modifier: Modifier = Modifier,
    decimalFormatter: DecimalFormatter
) {

    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = decimalFormatter.cleanup(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Decimal,
        ),
        visualTransformation = DecimalInputVisualTransformation(decimalFormatter)
    )
}