package mx.mikeni.calculator.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import mx.mikeni.calculator.R
import mx.mikeni.calculator.core.ONE
import mx.mikeni.calculator.core.ZERO
import mx.mikeni.calculator.ui.composables.DefaultTopAppBar
import mx.mikeni.calculator.ui.theme.Space12
import mx.mikeni.calculator.ui.theme.Space16
import mx.mikeni.calculator.ui.theme.Space4
import mx.mikeni.calculator.ui.theme.Space8

@Composable
fun BishopIndexScreen(
        onBackListener: () -> Unit,
        modifier: Modifier = Modifier
) {

    Scaffold(
            topBar = {
                DefaultTopAppBar(
                        title = stringResource(R.string.bishop_score_calculator_title),
                        onBackListener = onBackListener
                )
            },
            modifier = modifier
    ) { paddingValues ->
        BishopIndexContent(
                modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = Space16)
        )
    }
}

@Composable
private fun BishopIndexContent(
        modifier: Modifier = Modifier
) {
    var dilation by remember { mutableIntStateOf(-ONE) }
    var effacement by remember { mutableIntStateOf(-ONE) }
    var station by remember { mutableIntStateOf(-ONE) }
    var consistency by remember { mutableIntStateOf(-ONE) }
    var position by remember { mutableIntStateOf(-ONE) }

    val totalScore = dilation + effacement + station + consistency + position

    Column(modifier = modifier) {
        Text(
                text = stringResource(R.string.total_bishop_score, totalScore.takeIf { it >= ZERO } ?: "-"),
                style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(Space16))

        // Dilation
        AssessmentRow(
                label = stringResource(R.string.dilation_bishop_label),
                options = stringArrayResource(R.array.dilation_bishop_options),
                selectedOption = dilation,
                onSelectionChange = { dilation = it }
        )

        // Effacement (%)
        AssessmentRow(
                label = stringResource(R.string.effacement_bishop_label),
                options = stringArrayResource(R.array.effacement_bishop_options),
                selectedOption = effacement,
                onSelectionChange = { effacement = it }
        )

        // Consistency
        AssessmentRow(
                label = stringResource(R.string.consistency_bishop_label),
                options = stringArrayResource(R.array.consistency_bishop_options),
                selectedOption = consistency,
                onSelectionChange = { consistency = it }
        )

        // Position
        AssessmentRow(
                label = stringResource(R.string.position_bishop_label),
                options = stringArrayResource(R.array.position_bishop_options),
                selectedOption = position,
                onSelectionChange = { position = it }
        )

        // Station
        AssessmentRow(
                label = stringResource(R.string.station_bishop_label),
                options = stringArrayResource(R.array.station_bishop_options),
                selectedOption = station,
                onSelectionChange = { station = it }
        )
        AnimatedVisibility(
                visible = listOf(dilation, effacement, consistency, position, station).all { it != -ONE }
        ) {
            Column {
                Text(
                        text = stringResource(R.string.bishop_score_interpretation),
                        style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(Space8))
                Text(
                        text = stringResource(totalScore.getBishopScore()),
                        style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

private fun Int.getBishopScore() = when {
    this >= BishopHighScore -> R.string.bishop_score_interpretation_high
    this in BishopMediumScore until BishopHighScore -> R.string.bishop_score_interpretation_medium
    else -> R.string.bishop_score_interpretation_low
}

private const val BishopHighScore = 9
private const val BishopMediumScore = 6

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AssessmentRow(
        label: String,
        options: Array<String>,
        selectedOption: Int,
        onSelectionChange: (Int) -> Unit
) {
    Column {
        Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge
        )
        FlowRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed { index, option ->
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Space4),
                        modifier = Modifier
                                .clickable(
                                        onClick = { onSelectionChange(index) },
                                        interactionSource = null,
                                        indication = null
                                )
                                .padding(vertical = Space12, horizontal = Space4)

                ) {
                    RadioButton(
                            selected = index == selectedOption,
                            onClick = null
                    )
                    Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(Space8))
    }
}
