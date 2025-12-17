package com.diiage.template.ui.screens.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.template.ui.core.Destination
import com.diiage.template.ui.core.components.input.PrimaryButton
import com.diiage.template.ui.core.components.layout.CenteredBox
import com.diiage.template.ui.core.components.layout.CenteredColumn
import com.diiage.template.ui.core.components.layout.ExtraLargeSpacer
import com.diiage.template.ui.core.components.layout.MainScaffold
import com.diiage.template.ui.core.components.layout.MediumSpacer
import com.diiage.template.ui.core.navigate
import com.diiage.template.ui.core.theme.AppTheme

@Composable
fun HomeScreen(navController: NavController) {
    MainScaffold(navController = navController) {
        CenteredBox(horizontalPadding = 16.dp) {
            CenteredColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Bienvenue dans EduSec!",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )

                ExtraLargeSpacer()
                MediumSpacer()

                PrimaryButton(
                    onClick = { navController.navigate(Destination.Splash) },
                    text = "Redemarrer l'application"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(navController = rememberNavController())
    }
}
