package mx.mikeni.calculator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import mx.mikeni.calculator.R
import mx.mikeni.calculator.ui.theme.Pink80
import mx.mikeni.calculator.ui.theme.Space16
import mx.mikeni.calculator.ui.theme.Space24
import mx.mikeni.calculator.ui.theme.Space256
import mx.mikeni.calculator.ui.theme.Space48
import mx.mikeni.calculator.ui.theme.Space8

@Composable
fun HomeScreen(
        onCalculateByDateListener: () -> Unit,
        onCalculateByWeeksListener: () -> Unit,
        onCalculateByCapurroListener: () -> Unit,
        onBishopListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
    ) {
        Card(
                colors = CardDefaults.cardColors(containerColor = Pink80),
                shape = RoundedCornerShape(bottomStart = Space24, bottomEnd = Space24),
                modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Space48, bottom = Space24)
            ) {
                Image(
                        painter = painterResource(id = R.drawable.nurse),
                        contentDescription = null,
                        modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(Space256)
                )
            }
        }
        MenuItem(
                icon = R.drawable.ic_event_upcoming,
                text = stringResource(R.string.calculate_sdg_by_fum),
                onMenuItemListener = onCalculateByDateListener
        )
        MenuItem(
                icon = R.drawable.ic_ecg,
                text = stringResource(R.string.calculate_sdg_by_usg),
                onMenuItemListener = onCalculateByWeeksListener
        )
        MenuItem(
                icon = R.drawable.ic_child_care,
                text = stringResource(R.string.calculate_sdg_by_capurro),
                onMenuItemListener = onCalculateByCapurroListener
        )
        MenuItem(
                icon = R.drawable.ic_pregnancy,
                text = stringResource(R.string.calculate_bishop_score),
                onMenuItemListener = onBishopListener
        )
    }
}

@Composable
private fun MenuItem(
        icon: Int,
        text: String,
        onMenuItemListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
            contentPadding = PaddingValues(vertical = Space16, horizontal = Space24),
            onClick = onMenuItemListener,
            modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = Space24, horizontal = Space16)
    ) {
        Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
        ) {
            Row {
                Icon(painter = painterResource(icon), contentDescription = null)
                Spacer(modifier = Modifier.width(Space8))
                Text(text)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}
