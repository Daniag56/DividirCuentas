package com.example.dividircuentasv2.ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dividircuentasv2.ui.theme.DividirCuentasV2Theme


@Composable
@Preview
fun TipCalculatorScreenPreview(){
    DividirCuentasV2Theme {
        TipCalculatorScreen()
    }
}
@Composable
fun TipCalculatorScreen(){
    var totalAmount by remember { mutableStateOf("") }
    val guestNumberState = remember { TextFieldState() }
    var checked: Boolean = true
    var tipValue: Float = 0.0F
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding ->
        val columnModifier = Modifier.consumeWindowInsets(innerPadding)
            .padding(innerPadding)
        Column(
            modifier = columnModifier
        ) {
            val textFieldModifier = Modifier.fillMaxWidth()
                .padding(all = 8.dp)
            TextField(
                modifier = textFieldModifier,
                value = totalAmount,
                onValueChange = {
                    newText ->
                    totalAmount = newText
                },
            )
            TextField(
                state = guestNumberState,
                modifier = textFieldModifier,

            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Texto del Switch")
                Switch(
                    checked = checked,
                    onCheckedChange = {}
                )
            }
            Slider(
                value = tipValue,
                onValueChange = {}
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Calcular")
            }
            Text("")
        }
    }
}