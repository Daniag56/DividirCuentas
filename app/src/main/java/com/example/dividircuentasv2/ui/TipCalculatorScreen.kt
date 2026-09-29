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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import com.example.dividircuentasv2.R
import com.example.dividircuentasv2.ui.theme.DividirCuentasV2Theme
@Preview
@Composable
fun TipCalculatorScreenPreview() {
    DividirCuentasV2Theme {
        TipCalculatorScreen()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorScreen() {
    var totalAmount by remember { mutableStateOf("") }
    val guestNumberState = remember { TextFieldState() }
    var checked by remember { mutableStateOf(false) }
    var tipValue by rememberSaveable { mutableFloatStateOf(0f) }
    var resultado by rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val columnModifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(innerPadding)
        Column(
            modifier = columnModifier
        ) {
            val textFieldModifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            )
            TextField(
                modifier = textFieldModifier,
                value = totalAmount,
                keyboardOptions = customQuantityKeyboardOptions,
                onValueChange = { newText ->
                    totalAmount = newText
                }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.tipLable))
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (!checked) {
                            tipValue = 0f
                        }
                    }
                )
            }
            Slider(
                value = tipValue,
                onValueChange = {
                    tipValue = it
                },
                enabled = checked,
                valueRange = 0f..5f,
                steps = 3
            )
            Text(
                text = "Propina: ${(tipValue.toInt() * 5)}%"
            )
            val total = totalAmount.toDoubleOrNull()
            val isCalculateButtonEnable =
                guestNumberState.text.isNotBlank() &&
                        total != null &&
                        total > 0
            Button(
                enabled = isCalculateButtonEnable,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val guestNumber =
                        guestNumberState.text.toString().toIntOrNull()
                    val total =
                        totalAmount.toDoubleOrNull()
                    if (guestNumber != null &&
                        total != null && guestNumber > 0
                    ) {
                        val porcentaje =
                            if (checked) tipValue.toInt() * 5
                            else 0
                        val propina = total * (porcentaje / 100.0)
                        val totalConPropina = total + propina
                        val porPersona = totalConPropina / guestNumber
                        resultado =
                            "Total: ${"%.2f".format(totalConPropina)} €\n" +
                                    "Por persona: ${"%.2f".format(porPersona)} €"
                    }
                }
            ) {
                Text("Calcular")
            }
            if (resultado.isNotBlank()) {
                Text(
                    text = resultado,
                    modifier = Modifier

                )
            }
        }
    }
}