package mx.mikeni.calculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import mx.mikeni.calculator.R
import mx.mikeni.calculator.core.ONE
import mx.mikeni.calculator.core.ZERO
import mx.mikeni.calculator.core.convertMillisToDate
import mx.mikeni.calculator.core.daysTillNow
import mx.mikeni.calculator.core.localDateTimeFormat
import mx.mikeni.calculator.core.toDays
import mx.mikeni.calculator.core.toWeeks
import mx.mikeni.calculator.ui.composables.DatePickerFieldToModal
import mx.mikeni.calculator.ui.composables.DefaultTopAppBar
import mx.mikeni.calculator.ui.composables.NumberTextField
import mx.mikeni.calculator.ui.theme.Space16

@Composable
fun CalculateByWeeksScreen(
        onBackListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Scaffold(
            topBar = {
                DefaultTopAppBar(
                        title = stringResource(R.string.sdg_by_usg_calculator_title),
                        onBackListener = onBackListener
                )
            },
            modifier = modifier
    ) { paddingValues ->
        CalculateByWeeksContent(
                modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = Space16)
        )
    }
}

@Composable
private fun CalculateByWeeksContent(
        modifier: Modifier = Modifier
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    val localDateTime by remember { derivedStateOf { selectedDate?.let { convertMillisToDate(it) } } }
    var weeksValue by remember { mutableStateOf(String()) }
    var daysValue by remember { mutableStateOf(String()) }
    var showModal by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        DatePickerFieldToModal(
                date = localDateTime?.format(localDateTimeFormat).orEmpty(),
                showModal = showModal,
                onDateListener = { selectedDate = it },
                onModalListener = { showModal = it }
        )
        Spacer(modifier = Modifier.height(Space16))
        Row(horizontalArrangement = Arrangement.spacedBy(Space16)) {
            NumberTextField(
                    value = weeksValue,
                    label = stringResource(R.string.weeks),
                    onValueChange = { weeksValue = it },
                    modifier = Modifier.weight(ONE.toFloat())
            )
            NumberTextField(
                    value = daysValue,
                    label = stringResource(R.string.days),
                    onValueChange = { daysValue = it },
                    modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Space16))
        localDateTime?.let {
            Results(
                    localDateTime = it,
                    weeksThen = weeksValue.toIntOrNull() ?: ZERO,
                    daysThen = daysValue.toIntOrNull() ?: ZERO
            )
        }
    }
}

@Composable
private fun Results(
        localDateTime: LocalDateTime,
        weeksThen: Int,
        daysThen: Int
) {
    val daysTillNow = localDateTime.date.daysTillNow()
    val totalDays = daysTillNow + weeksThen.times(DateTimeUnit.WEEK.days) + daysThen
    Text(stringResource(R.string.total_weeks, totalDays.toWeeks(), totalDays.toDays()))
}
