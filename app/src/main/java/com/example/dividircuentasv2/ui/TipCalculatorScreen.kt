package com.example.dividircuentasv2.ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dividircuentasv2.R
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
    var checked by remember { mutableStateOf(false) }
    var tipValue by rememberSaveable { mutableFloatStateOf(0.0F) }
    var resultado by rememberSaveable {mutableStateOf("")}

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding ->
        val columnModifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(innerPadding)
        Column(
            modifier = columnModifier
        ) {
            val textFieldModifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
            val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            )
            TextField(
                modifier = textFieldModifier,
                value = totalAmount,
                keyboardOptions = customQuantityKeyboardOptions,
                onValueChange = {
                    newText ->
                    totalAmount = newText
                },
            )
            val customGuestKeyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
            TextField(
                state = guestNumberState,
                modifier = textFieldModifier,
                keyboardOptions = customGuestKeyboardOptions
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal =  8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(stringResource(R.string.tipLable))
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (!checked) tipValue = 0.0f
                    }
                )
            }
            Slider(
                value = tipValue,
                enabled = checked,
                onValueChange = {
                    tipValue = it

                    when{

                    }
                },
                steps = 3,
                valueRange = 0f..4f
            )
            val isCalculateButtonEnable = guestNumberState.text.isNotBlank()  && totalAmount.toFloat() > 0
            val  guestNumber = guestNumberState.text.toString().toIntOrNull()
            val totalAmount = totalAmount.toString().toDoubleOrNull()

            val isCalculateButtonEnabled = if (guestNumber != null && totalAmount != null)
                guestNumber > 0 && totalAmount > 0.0
                else
                    false
            Button(
                enabled = isCalculateButtonEnable,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                }
            ) {
                Text("Calcular")
                when(tipValue){
                    1.0f -> {

                    }
                    2.0f -> {

                    }
                }
            }
                val showCalculate = true
                if (showCalculate){

                }
        }
    }
}