package mx.mikeni.calculator.ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly

@Composable
fun NumberTextField(
        value: String,
        label: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier
) {
    OutlinedTextField(
            value = value,
            label = { Text(label) },
            onValueChange = { if (it.isDigitsOnly()) onValueChange(it) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            modifier = modifier
    )
}
