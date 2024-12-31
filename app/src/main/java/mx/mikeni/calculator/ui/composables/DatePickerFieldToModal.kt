package mx.mikeni.calculator.ui.composables

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import mx.mikeni.calculator.R

@Composable
fun DatePickerFieldToModal(
        date: String,
        showModal: Boolean,
        onDateListener: (Long?) -> Unit,
        onModalListener: (Boolean) -> Unit,
        modifier: Modifier = Modifier
) {
    OutlinedTextField(
            value = date,
            onValueChange = { },
            readOnly = true,
            label = { Text(stringResource(R.string.date)) },
            placeholder = { Text(stringResource(R.string.date_placeholder)) },
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.select_date))
            },
            modifier = modifier
                    .fillMaxWidth()
                    .pointerInput(date) {
                        awaitEachGesture {
                            // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                            // in the Initial pass to observe events before the text field consumes them
                            // in the Main pass.
                            awaitFirstDown(pass = PointerEventPass.Initial)
                            val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                            if (upEvent != null) onModalListener(true)
                        }
                    }
    )

    if (showModal) {
        DatePickerModal(
                onDateSelected = onDateListener,
                onDismiss = { onModalListener(false) }
        )
    }
}
