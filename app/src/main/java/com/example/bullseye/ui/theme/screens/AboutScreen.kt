package com.example.bullseye.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bullseye.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AboutScreen(
    onNavigateToBullseye: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.about_page_title)) },
                navigationIcon = {
                    FilledIconButton(onClick = {
                        onNavigateToBullseye()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_icon)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .fillMaxSize()
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.party_popper),
                    contentDescription = stringResource(R.string.image_congratulations),
                    modifier = Modifier.size(35.dp)
                )

                Text(
                    text = stringResource(id = R.string.about_title_text),
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
                )

                Image(
                    painter = painterResource(id = R.drawable.party_popper),
                    contentDescription = stringResource(R.string.image_congratulations),
                    modifier = Modifier.size(35.dp)
                )

            }

            Text(
                text = stringResource(id = R.string.about_bullseye_text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
            )
            Button(
                onClick = {
                    onNavigateToBullseye()
                },
                shape = MaterialTheme.shapes.medium
            ) {
               Text(text = stringResource(id = R.string.back_button))
            }
        }
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    AboutScreen( onNavigateToBullseye = {})
}