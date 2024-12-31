package mx.mikeni.calculator.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import mx.mikeni.calculator.R
import mx.mikeni.calculator.core.birthDate
import mx.mikeni.calculator.core.convertMillisToDate
import mx.mikeni.calculator.core.daysTillNow
import mx.mikeni.calculator.core.localDateFormat
import mx.mikeni.calculator.core.localDateTimeFormat
import mx.mikeni.calculator.core.toDays
import mx.mikeni.calculator.core.toWeeks
import mx.mikeni.calculator.ui.composables.DatePickerFieldToModal
import mx.mikeni.calculator.ui.composables.DefaultTopAppBar
import mx.mikeni.calculator.ui.theme.Space16
import mx.mikeni.calculator.ui.theme.Space8

@Composable
fun CalculateByDateScreen(
        onBackListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Scaffold(
            topBar = {
                DefaultTopAppBar(
                        title = stringResource(R.string.sdg_by_fum_calculator_title),
                        onBackListener = onBackListener
                )
            },
            modifier = modifier
    ) { paddingValues ->
        CalculateByDateContent(
                modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = Space16)
        )
    }
}

@Composable
private fun CalculateByDateContent(
        modifier: Modifier = Modifier
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    val localDateTime by remember { derivedStateOf { selectedDate?.let { convertMillisToDate(it) } } }
    var showModal by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        DatePickerFieldToModal(
                date = localDateTime?.format(localDateTimeFormat).orEmpty(),
                showModal = showModal,
                onDateListener = { selectedDate = it },
                onModalListener = { showModal = it }
        )
        Spacer(modifier = Modifier.height(Space16))
        localDateTime?.let {
            Results(it)
        }
    }
}

@Composable
private fun Results(
        localDateTime: LocalDateTime
) {
    val birthDay = localDateTime.date.birthDate()
    val daysTillNow = localDateTime.date.daysTillNow()
    Text(stringResource(R.string.estimated_date_of_delivery, birthDay.format(localDateFormat)))
    Spacer(modifier = Modifier.height(Space8))
    Text(stringResource(R.string.total_weeks, daysTillNow.toWeeks(), daysTillNow.toDays()))
}
