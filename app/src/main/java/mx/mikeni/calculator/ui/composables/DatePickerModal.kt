package mx.mikeni.calculator.ui.composables

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.datetime.number
import mx.mikeni.calculator.R
import mx.mikeni.calculator.core.ONE
import mx.mikeni.calculator.core.ZERO
import mx.mikeni.calculator.core.today

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
        onDateSelected: (Long?) -> Unit,
        onDismiss: () -> Unit
) {
    val today = today()
    val datePickerState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Input,
            yearRange = IntRange(today.year.minus(if (today.month.number > 10) ZERO else ONE), today.year),
    )

    DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                        onClick = {
                            onDateSelected(datePickerState.selectedDateMillis)
                            onDismiss()
                        },
                        content = {
                            Text(stringResource(R.string.ok))
                        }
                )
            },
            dismissButton = {
                TextButton(
                        onClick = onDismiss,
                        content = {
                            Text(stringResource(R.string.cancel))
                        }
                )
            },
            content = {
                DatePicker(state = datePickerState)
            }
    )
}
