package mx.mikeni.calculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mx.mikeni.calculator.ui.screens.BishopIndexScreen
import mx.mikeni.calculator.ui.screens.CalculateByCapurroScreen
import mx.mikeni.calculator.ui.screens.CalculateByDateScreen
import mx.mikeni.calculator.ui.screens.CalculateByWeeksScreen
import mx.mikeni.calculator.ui.screens.HomeScreen

@Composable
fun CalculatorApp() {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val message = stringResource(R.string.coming_soon)
    val comingSoon: () -> Unit = { scope.launch { snackBarHostState.showSnackbar(message) } }
    val popBackStack: () -> Unit = { if (navController.previousBackStackEntry != null) navController.popBackStack() }
    Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            }
    ) { paddingValues ->
        NavHost(
                navController = navController,
                startDestination = HomeScreen,
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            composable<HomeScreen> {
                HomeScreen(
                        onCalculateByDateListener = { navController.navigate(CalculateByDateScreen) },
                        onCalculateByWeeksListener = { navController.navigate(CalculateByWeeksScreen) },
                        onCalculateByCapurroListener = comingSoon,
                        onBishopListener = { navController.navigate(BishopIndexScreen) }
                )
            }
            composable<CalculateByDateScreen> {
                CalculateByDateScreen(
                        onBackListener = popBackStack
                )
            }
            composable<CalculateByWeeksScreen> {
                CalculateByWeeksScreen(
                        onBackListener = popBackStack
                )
            }
            composable<CalculateByCapurroScreen> {
                CalculateByWeeksScreen(
                        onBackListener = popBackStack
                )
            }
            composable<BishopIndexScreen> {
                BishopIndexScreen(
                        onBackListener = popBackStack
                )
            }
        }
    }
}

