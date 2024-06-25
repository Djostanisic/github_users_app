package com.djostanisic.githubusers.presentation.user_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.djostanisic.githubusers.domain.model.Organization


@Composable
fun OrganizationItem(
    organization: Organization
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
            AsyncImage(
                model = organization.avatarUrl,
                contentDescription = "Organization Avatar",
                contentScale = ContentScale.Fit,
                placeholder = rememberAsyncImagePainter(
                    model = Color.Gray // Placeholder color
                ),
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp)
            )
            Column {
                Text(
                    text = organization.organizationName,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = organization.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
        }
    }
}
